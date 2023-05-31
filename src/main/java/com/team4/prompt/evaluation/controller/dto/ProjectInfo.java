package com.team4.prompt.evaluation.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectInfo {
    private Long id;
    private String name;
    private String projectNumber;
    private String client;
    private int budget;

}
