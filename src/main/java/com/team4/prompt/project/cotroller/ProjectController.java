package com.team4.prompt.project.cotroller;

import com.team4.prompt.common.CurrentUser;
import com.team4.prompt.project.cotroller.dto.ProjectCreateRequest;
import com.team4.prompt.project.cotroller.dto.ProjectListDto;
import com.team4.prompt.project.domain.ProjectStatus;
import com.team4.prompt.project.service.ProjectService;
import com.team4.prompt.user.model.User;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ProjectListDto getAllProject(){
        return projectService.getAllProject();
    }

    @GetMapping("admin/in-progress")
    @PreAuthorize("hasRole('ADMIN')")
    public ProjectListDto getInProgressProject() {
        return projectService.getInProgressProject();
    }

    @GetMapping("admin/done")
    @PreAuthorize("hasRole('ADMIN')")
    public ProjectListDto getDoneProject() {
        return projectService.getDoneProject();
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")
    public ProjectListDto search(
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) String projectNumber,
            @RequestParam(required = false) String client,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyMMdd") LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyMMdd") LocalDateTime endDate) {
        return projectService.search(status, projectNumber, client, name, startDate, endDate);
    }

    @GetMapping("/all")
    public ProjectListDto getAllProjectFromEmployee(@CurrentUser User user) {
        return projectService.getAllProjectForEmployee(user);
    }

    @GetMapping("/in-progress")
    public ProjectListDto getInProgressProjectFromEmployee(@CurrentUser User user) {
        return projectService.getInProgressProjectForEmployee(user);
    }

    @GetMapping("/done")
    public ProjectListDto getDoneProjectFromEmployee(@CurrentUser User user) {
        return projectService.getDoneProjectForEmployee(user);
    }

}
