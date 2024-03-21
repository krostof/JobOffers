package com.example.joboffers.domain.loginandregister.dto;

import lombok.Builder;

@Builder
public record UserDto(Long id,String name,String password) {
}
