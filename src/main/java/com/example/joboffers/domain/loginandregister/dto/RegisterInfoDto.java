package com.example.joboffers.domain.loginandregister.dto;

import lombok.Builder;

@Builder
public record RegisterInfoDto(String id,String name, String message) {
}
