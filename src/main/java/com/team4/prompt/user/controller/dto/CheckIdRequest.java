package com.team4.prompt.user.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CheckIdRequest {
    @NotBlank
    private String userId;
}
