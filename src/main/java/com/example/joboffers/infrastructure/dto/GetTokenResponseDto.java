package com.example.joboffers.infrastructure.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;
@Builder
public record GetTokenResponseDto(
        String username,
        String message
) {
}
