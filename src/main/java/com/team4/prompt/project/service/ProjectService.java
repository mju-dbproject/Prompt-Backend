package com.team4.prompt.project.service;

import com.team4.prompt.project.cotroller.dto.ProjectCreateRequest;
import com.team4.prompt.project.cotroller.dto.ProjectDto;
import com.team4.prompt.project.cotroller.dto.ProjectListDto;
import com.team4.prompt.project.domain.Project;
import com.team4.prompt.project.domain.ProjectStatus;
import com.team4.prompt.project.repository.ProjectRepository;
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
}
