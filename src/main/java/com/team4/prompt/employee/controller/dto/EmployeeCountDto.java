package com.team4.prompt.employee.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCountDto {
    int all;
    int developer;
    int noDeveloper;
}
