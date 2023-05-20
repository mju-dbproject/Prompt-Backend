package com.team4.prompt.project.cotroller;

import com.team4.prompt.common.CurrentUser;
import com.team4.prompt.project.cotroller.dto.ProjectCreateRequest;
import com.team4.prompt.project.cotroller.dto.ProjectListDto;
import com.team4.prompt.project.service.ProjectService;
import com.team4.prompt.user.model.User;
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

    @PostMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public void createProject(@RequestBody @Valid ProjectCreateRequest projectCreateRequest) {
        projectService.createProject(projectCreateRequest);
    }

    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ProjectListDto getAllProjectForAdmin(){
        return projectService.getAllProjectForAdmin();
    }

    @GetMapping("admin/in-progress")
    @PreAuthorize("hasRole('ADMIN')")
    public ProjectListDto getInProgressProjectForAdmin() {
        return projectService.getInProgressProjectForAdmin();
    }

    @GetMapping("admin/done")
    @PreAuthorize("hasRole('ADMIN')")
    public ProjectListDto getDoneProjectForAdmin() {
        return projectService.getDoneProjectForAdmin();
    }

    @GetMapping("/all")
    public ProjectListDto getAllProjectFromEmployee(@CurrentUser User user) {
        return projectService.getAllProjectForEmployee(user);
    }

    @GetMapping("/in-progress")
    public ProjectListDto getInProgressProjectFromEmployee(@CurrentUser User user) {
        return projectService.getInProgressProjectForEmployee(user);
    }


}
