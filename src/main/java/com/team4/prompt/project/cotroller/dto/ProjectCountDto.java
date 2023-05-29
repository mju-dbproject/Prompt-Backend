package com.team4.prompt.project.cotroller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCountDto {
    int all;
    int ready;
    int progress;
    int finish;
    int delete;

}
