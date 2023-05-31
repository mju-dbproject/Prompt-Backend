package com.team4.prompt.evaluation.controller.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EvaluationDto {
    private final Long projectId;
    private final Long evaluatedId;
    private final String performance;
    private final String communication;
    private final String contents;
    private final String type;


    public EvaluationDto(Long projectId, Long evaluatedId, String performance, String communication,
                         String contents, String type) {
        this.projectId = projectId;
        this.evaluatedId = evaluatedId;
        this.communication = communication;
        this.performance = performance;
        this.contents = contents;
        this.type = type;

    }

}
