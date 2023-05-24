package com.team4.prompt.evaluation.controller;

import com.team4.prompt.common.CurrentUser;
import com.team4.prompt.evaluation.service.EvaluationService;
import com.team4.prompt.project.cotroller.dto.ProjectDto;
import com.team4.prompt.project.cotroller.dto.ProjectListDto;
import com.team4.prompt.project.service.ProjectService;
import com.team4.prompt.user.model.User;
import com.team4.prompt.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/evaluation")
@RequiredArgsConstructor
public class EvaluationController {
    private final ProjectService projectService;
    private final EvaluationService evaluationService;
    private final UserService userService;

    @GetMapping("/project/list")
    public ProjectListDto getProjectsForEvaluation(@CurrentUser User user) {
        return evaluationService.getProjectsForEvaluation(user);
    }

    @GetMapping("/employee/list")
    public List<String> getProjectPeer(@RequestBody Long projectId, @CurrentUser User user) {
        return evaluationService.getProjectPeer(projectId, user);
    }

    @GetMapping("/admin/type")
    @PreAuthorize("hasRole('ADMIN')")
    public List<String> gerEvaluationTypeForAdmin(@RequestBody Long projectId, @CurrentUser User user) {
        return evaluationService.getAvailableEvaluationsForAdmin(projectId, user);
    }

    @GetMapping("/user/type")
    @PreAuthorize("hasRole('USER')")
    public List<String> getEvaluationsForEmployee(@CurrentUser User user) {
        return evaluationService.getAvailableEvaluationsForEmployee(user);
    }

    @GetMapping("/project/detail")
    public ProjectDto getProjectDetails(@RequestBody Long projectId) {
        return evaluationService.getProjectDetails(projectId);
    }

    //@PostMapping("/save")
    //public void saveEvaluation(
    //        @PathVariable String projectId,
    //        @RequestParam String evaluationType,
    //        @RequestParam String targetUserId,
    //        @RequestParam String comment) {
    //    projectService.saveEvaluation(projectId, evaluationType, targetUserId, comment);
    //}

//}

}
