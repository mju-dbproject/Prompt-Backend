package com.team4.prompt.evaluation.controller.dto;

public class EvaluationPeerDto {
    private Long id;
    private String name;

    public EvaluationPeerDto(Long id, String name) {
        this.name = name;
        this.id = id;
    }
}