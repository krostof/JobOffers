package com.example.joboffers.domain.loginandregister;

import com.example.joboffers.domain.loginandregister.dto.RegisterInfoDto;
import com.example.joboffers.domain.loginandregister.dto.UserDto;
import com.example.joboffers.domain.loginandregister.dto.UserRegisterDto;
import lombok.AllArgsConstructor;

import static com.example.joboffers.domain.loginandregister.UserMapper.mapUserRegisterDtoToUser;

@AllArgsConstructor
class LoginAndRegisterFacade {

    private final LoginRepository loginRepository;
    final String REGISTER_MESSAGE = "User registered successfully";
    final String USER_NOT_FOUND = "User not found";

    UserDto findByUsername(String name) {
      return loginRepository.findUser(name)
                    .map(UserMapper::mapUserToUserDto)
                    .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
    }

    RegisterInfoDto register(UserRegisterDto dto) {
        Users users = loginRepository.save(mapUserRegisterDtoToUser(dto));
        return UserMapper.mapUserToRegisterInfoDto(users,REGISTER_MESSAGE);
    }



}
