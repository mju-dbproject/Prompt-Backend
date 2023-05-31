package com.team4.prompt.evaluation.controller.dto;

import com.team4.prompt.manpower.domain.ManPower;
import com.team4.prompt.project.domain.Project;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CanEvaluateProject {
    private Long projectId;
    private String projectName;
    private List<String> evaluationType;
    private List<EvaluatedDto> coworker;

}
