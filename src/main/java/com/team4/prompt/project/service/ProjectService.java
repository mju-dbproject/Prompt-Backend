package com.team4.prompt.project.service;

import com.team4.prompt.manpower.domain.ManPower;
import com.team4.prompt.manpower.domain.Task;
import com.team4.prompt.manpower.repository.ManpowerRepository;
import com.team4.prompt.manpower.service.ManpowerService;
import com.team4.prompt.project.cotroller.dto.ProjectCreateRequest;
import com.team4.prompt.project.cotroller.dto.ProjectDto;
import com.team4.prompt.project.cotroller.dto.ProjectListDto;
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

    public ProjectListDto getAllProject(){
        List<Project> projectList = projectRepository.findAll();
        List<ProjectDto> projectDtoList = projectList.stream().map(ProjectDto::new).toList();
        return new ProjectListDto(projectDtoList);
    }

    public ProjectListDto getInProgressProject(){
        List<Project> projectList = projectRepository.findByStatus(ProjectStatus.PROGRESS);
        List<ProjectDto> projectDtoList = projectList.stream().map(ProjectDto::new).toList();
        return new ProjectListDto(projectDtoList);
    }

    public ProjectListDto getDoneProject() {
        List<Project> projectList = projectRepository.findByStatus(ProjectStatus.FINISH);
        List<ProjectDto> projectDtoList = projectList.stream().map(ProjectDto::new).toList();
        return new ProjectListDto(projectDtoList);
    }

    public ProjectListDto getAllProjectForEmployee(User user) {
        List<ManPower> manpowerList = manpowerRepository.findByUser(user);
        List<ProjectDto> projectDtoList = manpowerList.stream().map(ManPower::getProject).map(ProjectDto::new).toList();
        return new ProjectListDto(projectDtoList);
    }

    public ProjectListDto getInProgressProjectForEmployee(User user) {
        List<ManPower> manpowerList = manpowerRepository.findByUser(user);
        List<ProjectDto> projectDtoList = manpowerList.stream().map(ManPower::getProject)
                .filter(project -> project.getStatus().equals(ProjectStatus.PROGRESS))
                .map(ProjectDto::new).toList();
        return new ProjectListDto(projectDtoList);
    }

    public ProjectListDto getDoneProjectForEmployee(User user) {
        List<ManPower> manpowerList = manpowerRepository.findByUser(user);
        List<ProjectDto> projectDtoList = manpowerList.stream().map(ManPower::getProject)
                .filter(project -> project.getStatus().equals(ProjectStatus.FINISH))
                .map(ProjectDto::new).toList();
        return new ProjectListDto(projectDtoList);
    }

    public ProjectListDto search(Integer status, String projectNumber, String client, String name, LocalDateTime startDate, LocalDateTime endDate) {
        return new ProjectListDto(projectRepository.findBySearchOption(status, projectNumber, client, name, startDate, endDate)
                .stream().map(ProjectDto::new).toList());
    }
}
