package com.team4.prompt.user.controller.dto;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String userId;
    private String checkPassword;
    private String newPassword;
}
