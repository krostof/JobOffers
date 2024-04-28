package com.example.joboffers.infrastructure.security;

import com.example.joboffers.infrastructure.dto.GetTokenRequestDto;
import com.example.joboffers.infrastructure.dto.GetTokenResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class JwtAuthenticatorFacade {

    private final AuthenticationManager authenticationManager;

    public GetTokenResponseDto authenticateAndGenerateToken(GetTokenRequestDto loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
    return GetTokenResponseDto.builder().build();
    }

}
