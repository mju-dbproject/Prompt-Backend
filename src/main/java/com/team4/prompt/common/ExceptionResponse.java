package com.team4.prompt.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExceptionResponse {
    private final int status;
    private final String message;

    public ExceptionResponse(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }
}
