package com.example.joboffers.infrastructure.apivalidation;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ApiValidationErrorResponseDto(List<String> messages,
                                     HttpStatus status) {
}
