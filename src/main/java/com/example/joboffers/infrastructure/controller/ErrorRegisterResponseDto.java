package com.example.joboffers.infrastructure.controller;

import org.springframework.http.HttpStatus;

public record ErrorRegisterResponseDto(String message, HttpStatus status) {
}
