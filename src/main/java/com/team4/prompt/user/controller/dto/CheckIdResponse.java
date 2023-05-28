package com.team4.prompt.user.controller.dto;

import lombok.Getter;

@Getter
public class CheckIdResponse {
    private final boolean isNotDuplicated;

    public CheckIdResponse(boolean isNotDuplicated) {
        this.isNotDuplicated = isNotDuplicated;
    }

}