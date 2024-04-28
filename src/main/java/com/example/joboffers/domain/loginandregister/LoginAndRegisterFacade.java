package com.example.joboffers.domain.loginandregister;

import com.example.joboffers.domain.loginandregister.dto.RegisterInfoDto;
import com.example.joboffers.domain.loginandregister.dto.UserDto;
import com.example.joboffers.domain.loginandregister.dto.UserRegisterDto;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import static com.example.joboffers.domain.loginandregister.UserMapper.mapUserRegisterDtoToUser;

@Component
@AllArgsConstructor
public class LoginAndRegisterFacade {

    private final LoginRepository loginRepository;
    final String REGISTER_MESSAGE = "User registered successfully";
    final String USER_NOT_FOUND = "User not found";

    public UserDto findByUsername(String name) {
      return loginRepository.findUsersByUsername(name)
                    .map(UserMapper::mapUserToUserDto)
                    .orElseThrow(() -> new BadCredentialsException(USER_NOT_FOUND));
    }

    RegisterInfoDto register(UserRegisterDto dto) {
        Users users = loginRepository.save(mapUserRegisterDtoToUser(dto));
        return UserMapper.mapUserToRegisterInfoDto(users,REGISTER_MESSAGE);
    }



}
