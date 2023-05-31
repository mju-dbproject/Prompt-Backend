package com.team4.prompt.evaluation.controller.dto;

import com.team4.prompt.project.domain.Project;
import lombok.Getter;

@Getter
public class EvaluationDetail {
    ProjectInfo projectInfo;
    EvaluationInfo evaluationInfo;

    public EvaluationDetail(ProjectInfo projectInfo, EvaluationInfo evaluationInfo) {
        this.projectInfo = projectInfo;
        this.evaluationInfo = evaluationInfo;
    }

}
