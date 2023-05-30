package com.team4.prompt.auth.controller.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponse {
    private String tokenType;
    private String accessToken;
    private String name;
    private String role;

    public LoginResponse(String tokenType, String accessToken, String name, String role) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.name = name;
        this.role = role;
    }
}
