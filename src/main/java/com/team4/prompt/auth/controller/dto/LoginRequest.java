package com.team4.prompt.auth.controller.dto;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String userId;
    private String password;
}
