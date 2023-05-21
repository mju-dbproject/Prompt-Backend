package com.team4.prompt.manpower.controller.dto;

import com.team4.prompt.manpower.domain.Task;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManpowerDto {
    private String employeeNumber;
    private String name;
    private String skill;
    private String rank;
    private String position;
    private Task task;

}
