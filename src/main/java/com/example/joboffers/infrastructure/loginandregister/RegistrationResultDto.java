package com.example.joboffers.infrastructure.loginandregister;

import org.springframework.http.HttpStatus;

public record RegistrationResultDto(
        String username,
        String message,
        HttpStatus status) {
}
