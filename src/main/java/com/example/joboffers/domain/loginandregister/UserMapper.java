package com.example.joboffers.domain.loginandregister;

import com.example.joboffers.domain.loginandregister.dto.RegisterInfoDto;
import com.example.joboffers.domain.loginandregister.dto.UserDto;
import com.example.joboffers.domain.loginandregister.dto.UserRegisterDto;

class UserMapper {
    static Users mapUserRegisterDtoToUser(final UserRegisterDto dto) {
        return Users.builder()
                .username(dto.name())
                .password(dto.password())
                .build();
    }

    static UserDto mapUserToUserDto(final Users users) {
        return UserDto.builder()
                .id(users.getId())
                .name(users.getUsername())
                .password(users.getPassword())
                .build();
    }

    static RegisterInfoDto mapUserToRegisterInfoDto(final Users users, String message) {
        return RegisterInfoDto.builder()
                .message(message)
                .name(users.getUsername())
                .build();
    }
}
