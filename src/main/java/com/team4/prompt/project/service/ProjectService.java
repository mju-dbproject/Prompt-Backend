package com.team4.prompt.project.service;

import com.team4.prompt.manpower.domain.ManPower;
import com.team4.prompt.manpower.domain.Task;
import com.team4.prompt.manpower.repository.ManpowerRepository;
import com.team4.prompt.manpower.service.ManpowerService;
import com.team4.prompt.project.cotroller.dto.ProjectCountDto;
import com.team4.prompt.project.cotroller.dto.ProjectCreateRequest;
import com.team4.prompt.project.cotroller.dto.ProjectDetailsDto;
import com.team4.prompt.project.cotroller.dto.ProjectDto;
import com.team4.prompt.project.cotroller.dto.ProjectListDto;
import com.team4.prompt.project.cotroller.dto.ProjectUpdateRequest;
import com.team4.prompt.project.domain.Project;
import com.team4.prompt.project.domain.ProjectStatus;
import com.team4.prompt.project.repository.ProjectRepository;
import com.team4.prompt.user.model.User;
import com.team4.prompt.user.service.UserService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ManpowerService manpowerService;
    private final ManpowerRepository manpowerRepository;
    private final UserService userService;

    @Transactional
    public void createProject(ProjectCreateRequest projectCreateRequest) {
        LocalDateTime now = LocalDateTime.now();
        Project newProject = Project
                .builder()
                .name(projectCreateRequest.getName())
                .client(projectCreateRequest.getClient())
                .budget(projectCreateRequest.getBudget())
                .description(projectCreateRequest.getDescription())
                .createDate(now)
                .status(ProjectStatus.READY)
                .build();

        int createOrder = projectRepository.findCreateCountInMonth(now);
        newProject.giveProjectNumber(createOrder);
        projectCreateRequest.getEmployeeList().forEach(projectEmployee -> {
            User user = userService.findUserById(projectEmployee.getId());
            ManPower manPower = ManPower
                    .builder()
                    .project(newProject)
                    .user(user)
                    .task(Task.of(projectEmployee.getTask()))
                    .startDate(LocalDateTime.now())
                    .build();
            newProject.addManpower(manPower);
        });
        projectRepository.save(newProject);
    }

    @Transactional
    public void updateProject(Long id, ProjectUpdateRequest projectUpdateRequest) {
        Project project = findProjectById(id);
        project.update(projectUpdateRequest.getName(), projectUpdateRequest.getClient(),
                projectUpdateRequest.getBudget(), projectUpdateRequest.getDescription());
        projectUpdateRequest.getAddEmployeeList().forEach(addEmployee -> {
            User user = userService.findUserById(addEmployee.getId());
            manpowerService.checkAlreadyManpower(user, project);
            ManPower manPower = ManPower
                    .builder()
                    .project(project)
                    .user(user)
                    .task(Task.of(addEmployee.getTask()))
                    .startDate(LocalDateTime.now())
                    .build();
            project.addManpower(manPower);
        });
        projectRepository.save(project);

        projectUpdateRequest.getEndManpowerList().forEach(endManpowerId -> {
            ManPower manpower = manpowerService.findManpowerById(endManpowerId);
            manpower.finish();
            manpowerRepository.save(manpower);
        });
    }

    public ProjectDetailsDto getProjectDetails(Long id) {
        Project project = findProjectById(id);
        return ProjectDetailsDto.from(project);
    }

    public ProjectCountDto getProjectCount() {
        int ready = projectRepository.findByStatus(ProjectStatus.READY).size();
        int progress = projectRepository.findByStatus(ProjectStatus.PROGRESS).size();
        int finish = projectRepository.findByStatus(ProjectStatus.FINISH).size();
        int cancel = projectRepository.findByStatus(ProjectStatus.CANCEL).size();

        return new ProjectCountDto(ready+progress+finish+cancel, ready, progress, finish, cancel);
    }

    public ProjectListDto getAllProjectForEmployee(User user, Integer status) {
        List<ManPower> manpowerList = manpowerRepository.findByUser(user);
        if(status == null){
            List<ProjectDto> projectDtoList = manpowerList.stream().map(ManPower::getProject).map(ProjectDto::new).toList();
            return new ProjectListDto(projectDtoList);
        }
        List<ProjectDto> projectDtoList = manpowerList.stream().map(ManPower::getProject)
                .filter(project -> project.getStatus().equals(ProjectStatus.of(status)))
                .map(ProjectDto::new).toList();
        return new ProjectListDto(projectDtoList);
    }

    public ProjectListDto search(Integer status, String projectNumber, String client, String name, String startDate, String endDate) {
        return new ProjectListDto(projectRepository.findBySearchOption(status, projectNumber, client, name, startDate, endDate)
                .stream().map(ProjectDto::new).toList());
    }

    public Project findProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(""));
    }

}
