package com.team4.prompt.manpower.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ManpowerListDto {
    List<ManpowerDto> manpowerList;
}
