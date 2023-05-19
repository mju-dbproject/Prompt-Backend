package com.team4.prompt.project.cotroller;

import com.team4.prompt.project.cotroller.dto.ProjectCreateRequest;
import com.team4.prompt.project.cotroller.dto.ProjectListDto;
import com.team4.prompt.project.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void createProject(@RequestBody @Valid ProjectCreateRequest projectCreateRequest) {
        projectService.createProject(projectCreateRequest);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ProjectListDto getAllProject(){
        return projectService.getAllProject();
    }

    @GetMapping("/in-progress")
    @PreAuthorize("hasRole('ADMIN')")
    public ProjectListDto getInProgressProject() {
        return projectService.getInProgressProject();
    }

    @GetMapping("/done")
    public ProjectListDto getDoneProject() {
        return projectService.getDoneProject();
    }
}
