package com.team4.prompt.evaluation.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationListDto {
    List<EvaluationDto> evaluations;
}
