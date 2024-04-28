package com.example.joboffers.domain.loginandregister.dto;

import lombok.Builder;

@Builder
public record UserDto(String id,String name,String password) {
}
