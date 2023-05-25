package com.team4.prompt.evaluation.controller;

import com.team4.prompt.common.CurrentUser;
import com.team4.prompt.evaluation.controller.dto.EvaluationDto;
import com.team4.prompt.evaluation.controller.dto.EvaluationListDto;
import com.team4.prompt.evaluation.service.EvaluationService;
import com.team4.prompt.project.cotroller.dto.ProjectDto;
import com.team4.prompt.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/evaluation")
@RequiredArgsConstructor
public class EvaluationController {
    private final EvaluationService evaluationService;

    @GetMapping("/project/list")
    public List<ProjectDto> getProjectsForEvaluation(@CurrentUser User user) {
        return evaluationService.getProjectsForEvaluation(user);
    }

    @GetMapping("/employee/list")
    public List<String> getProjectPeer(@RequestParam Long projectId, @CurrentUser User user) {
        return evaluationService.getProjectPeer(projectId, user);
    }

    @GetMapping("/type")
    public List<String> gerEvaluationTypeForAdmin(@RequestParam Long projectId, @CurrentUser User user) {
        return evaluationService.getAvailableEvaluations(projectId, user);
    }

    @GetMapping("/project/detail")
    public ProjectDto getProjectDetails(@RequestParam Long projectId) {
        return evaluationService.getProjectDetails(projectId);
    }

    @PostMapping("/save")
    public void saveEvaluation(@CurrentUser User user, @RequestBody EvaluationDto evaluationDto) {
        evaluationService.saveEvaluation(user, evaluationDto);
    }

    @GetMapping("/projects/{projectId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EvaluationListDto> getEvaluationsByProjectId(@PathVariable Long projectId, @CurrentUser User user) {
        List<EvaluationDto> evaluations = evaluationService.getEvaluationsByProjectId(projectId);
        EvaluationListDto evaluationListDto = new EvaluationListDto(evaluations);
        return ResponseEntity.ok(evaluationListDto);
    }

    @GetMapping("/employees/{evaluatedId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EvaluationListDto> getEvaluationByEvaluatedId(@PathVariable Long evaluatedId, @CurrentUser User user) {
        List<EvaluationDto> evaluations = evaluationService.getEvaluationsByEvaluatedId(evaluatedId);
        EvaluationListDto evaluationListDto = new EvaluationListDto(evaluations);
        return ResponseEntity.ok(evaluationListDto);
    }

}
