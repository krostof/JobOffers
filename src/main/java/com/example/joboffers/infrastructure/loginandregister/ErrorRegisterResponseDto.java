package com.example.joboffers.infrastructure.loginandregister;

import org.springframework.http.HttpStatus;

public record ErrorRegisterResponseDto(String message, HttpStatus status) {
}
