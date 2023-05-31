package com.team4.prompt.evaluation.controller.dto;

import lombok.Getter;

@Getter
public class DoneEvaluateRequest {
    private Long projectId;
    private String evaluationType;
    private Long evaluatedId;
}
