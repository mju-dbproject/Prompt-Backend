package com.team4.prompt.evaluation.controller;

import com.team4.prompt.common.CurrentUser;
import com.team4.prompt.evaluation.service.EvaluationService;
import com.team4.prompt.project.cotroller.dto.ProjectListDto;
import com.team4.prompt.project.service.ProjectService;
import com.team4.prompt.user.model.User;
import com.team4.prompt.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    //@GetMapping("/employee/list")
    //public List<String> getProjectPeer(@PathVariable String projectNumber, @CurrentUser User user) {
    //    return evaluationService.getProjectPeer(projectNumber, user);
    //}

    //@GetMapping("/admin/type")
    //@PreAuthorize("hasRole('ADMIN')")
    //public List<String> gerEvaluationTypeForAdmin(@CurrentUser User user) {
    //    return evaluationService.getAvailableEvaluationsForAdmin(user);
    //}

    //@GetMapping("/employee/type")
    //public List<String> geteEvaluationsForEmployee(@CurrentUser User user) {
    //    return evaluationService.getAvailableEvaluationsForEmployee(user);
    //}

    //@GetMapping("/project/detail")
    //public ProjectDto getProjectDetails(@RequestBody Long projectId) {
    //    return evaluationService.getProjectDetails(projectId);
    //}

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
