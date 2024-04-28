package com.example.joboffers.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;

public record GetTokenRequestDto(
        @NotBlank(message = "{username.not.blank}")
        String username,
        @NotBlank(message = "{password.not.blank}")
        String password) {
}
