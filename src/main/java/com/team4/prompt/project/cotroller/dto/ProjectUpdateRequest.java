package com.team4.prompt.project.cotroller.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class ProjectUpdateRequest {
    private String name;
    private String client;
    private int budget;
    private String description;
    private List<ProjectEmployeeRequest> addEmployeeList;
    private List<Long> endManpowerList;
}
