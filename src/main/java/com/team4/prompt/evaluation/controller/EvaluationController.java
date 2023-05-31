package com.team4.prompt.evaluation.controller;

import com.team4.prompt.common.CurrentUser;
import com.team4.prompt.evaluation.controller.dto.CanEvaluatedProjectList;
import com.team4.prompt.evaluation.controller.dto.DoneEvaluateRequest;
import com.team4.prompt.evaluation.controller.dto.DoneEvaluateResponse;
import com.team4.prompt.evaluation.controller.dto.EvaluationDetail;
import com.team4.prompt.evaluation.controller.dto.EvaluationDto;
import com.team4.prompt.evaluation.controller.dto.EvaluationListDto;
import com.team4.prompt.evaluation.service.EvaluationService;
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

    @GetMapping("/projects")
    public CanEvaluatedProjectList getProjectsForEvaluation(@CurrentUser User user) {
        return evaluationService.getProjectsForEvaluation(user);
    }

    @PostMapping("/check")
    public DoneEvaluateResponse checkDone(@CurrentUser User user, @RequestBody DoneEvaluateRequest doneEvaluateRequest){
        return evaluationService.checkDone(user, doneEvaluateRequest);
    }

    @PostMapping("/info")
    public EvaluationDetail info(@CurrentUser User user, @RequestBody DoneEvaluateRequest doneEvaluateRequest) {
        return evaluationService.getEvaluationDetails(user, doneEvaluateRequest);
    }

    @PostMapping()
    public void saveEvaluation(@CurrentUser User user, @RequestBody EvaluationDto evaluationDto) {
        evaluationService.saveEvaluation(user, evaluationDto);
    }

}
