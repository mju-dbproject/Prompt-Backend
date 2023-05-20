package com.team4.prompt.user.controller.dto;

import java.util.Optional;

public record UserUpdateDto(Optional<String> name, Optional<String> email, Optional<String> skill,
                            Optional<String> phoneNumber) {
}
