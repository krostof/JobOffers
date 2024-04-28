package com.example.joboffers.infrastructure.controller;

import com.example.joboffers.infrastructure.dto.GetTokenRequestDto;
import com.example.joboffers.infrastructure.dto.GetTokenResponseDto;
import com.example.joboffers.infrastructure.security.JwtAuthenticatorFacade;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@AllArgsConstructor
public class LoginAndRegisterRestController {

    private final JwtAuthenticatorFacade jwtAuthenticatorFacade;

    @PostMapping("/token")
    public ResponseEntity<GetTokenResponseDto> getToken(@RequestBody GetTokenRequestDto requestDto){
        jwtAuthenticatorFacade.authenticateAndGenerateToken(requestDto);
        GetTokenResponseDto dto = new GetTokenResponseDto(requestDto.username(),"Token was successfully granted!");
        log.info(requestDto.username()+" got token.");
        return ResponseEntity.ok(dto);
    }

}
