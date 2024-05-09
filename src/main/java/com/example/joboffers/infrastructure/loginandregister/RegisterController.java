package com.example.joboffers.infrastructure.loginandregister;

import com.example.joboffers.domain.loginandregister.LoginAndRegisterFacade;
import com.example.joboffers.domain.loginandregister.dto.RegisterInfoDto;
import com.example.joboffers.domain.loginandregister.dto.UserRegisterDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@AllArgsConstructor
public class RegisterController {

    private final LoginAndRegisterFacade loginAndRegisterFacade;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResultDto> register(@RequestBody RegistrationRequestDto requestDto) {
        RegistrationResultDto resultDto = processRegistration(requestDto);
        log.info("User registered: " + requestDto.username() + " " + resultDto);
        return new ResponseEntity<>(resultDto, resultDto.status());
    }

    private RegistrationResultDto processRegistration(RegistrationRequestDto requestDto){
        String encodedPassword = passwordEncoder.encode(requestDto.password());
        RegisterInfoDto userRegisterDto = loginAndRegisterFacade.register(new UserRegisterDto(requestDto.username(), encodedPassword));
        return new RegistrationResultDto(requestDto.username(), userRegisterDto.message(), HttpStatus.CREATED);
    }

}
