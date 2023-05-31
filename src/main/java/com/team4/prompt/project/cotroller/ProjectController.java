package com.team4.prompt.project.cotroller;

import com.team4.prompt.common.CurrentUser;
import com.team4.prompt.project.cotroller.dto.*;
import com.team4.prompt.project.service.ProjectService;
import com.team4.prompt.user.model.User;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/admin/project")
    @PreAuthorize("hasRole('ADMIN')")
    public void createProject(@RequestBody @Valid ProjectCreateRequest projectCreateRequest) {
        projectService.createProject(projectCreateRequest);
    }

    @PostMapping("/admin/project/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateProject(@PathVariable Long id, @RequestBody @Valid ProjectUpdateRequest projectUpdateRequest) {
        projectService.updateProject(id, projectUpdateRequest);
    }

    @PatchMapping("/admin/project/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void changProjectStatus(@PathVariable Long id, Map<String , Integer> projectStatus) {
        projectService.changeProjectStatus(id, projectStatus.get("status"));
    }

    @GetMapping("/project/{id}")
    public ProjectDetailsDto getProjectDetails(@PathVariable Long id) {
        return projectService.getProjectDetails(id);
    }

    @GetMapping("/admin/project/search")
    @PreAuthorize("hasRole('ADMIN')")
    public ProjectListDto search(
            @RequestParam Integer status,
            @RequestParam(required = false) String projectNumber,
            @RequestParam(required = false) String client,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        return projectService.search(status, projectNumber, client, name, startDate, endDate);
    }

    @GetMapping("/admin/project/count")
    public ProjectCountDto getProjectCount() {
        return projectService.getProjectCount();
    }

    @GetMapping("/project")
    public ProjectListDto getAllProjectFromEmployee(@CurrentUser User user, @RequestParam(required = false) Integer status) {
        return projectService.getAllProjectForEmployee(user, status);
    }
    @GetMapping("/project/count")
    public ProjectCountFromEmployeeDto getProjectCountFromEmployee(@CurrentUser User user) {
        return projectService.getProjectCountFromEmployee(user);
    }

}
