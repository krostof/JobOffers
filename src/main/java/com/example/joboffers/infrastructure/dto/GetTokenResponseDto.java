package com.example.joboffers.infrastructure.dto;

import lombok.Builder;

@Builder
public record GetTokenResponseDto(
        String username,
        String token,
        String message
) {
}
