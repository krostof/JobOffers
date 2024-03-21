package com.example.joboffers.domain.loginandregister.dto;

import lombok.Builder;

@Builder
public record RegisterInfoDto(String name, String message) {
}
