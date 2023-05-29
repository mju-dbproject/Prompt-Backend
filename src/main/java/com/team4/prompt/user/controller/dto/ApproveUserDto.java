package com.team4.prompt.user.controller.dto;

import lombok.Getter;

@Getter
public class ApproveUserDto {
    Long id;
    String name;

    public ApproveUserDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
