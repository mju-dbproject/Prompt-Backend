package com.team4.prompt.project.cotroller.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Getter;

@Getter
public class ProjectCreateRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String client;

    private int budget;

    private String description;

    private List<ProjectEmployee> employeeList;
}
