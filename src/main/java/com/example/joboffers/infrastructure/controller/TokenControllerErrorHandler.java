package com.example.joboffers.infrastructure.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
class TokenControllerErrorHandler {

    private static final String WARN_MSG = "Bad Credentials";

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorRegisterResponseDto> offerNotFoundException(BadCredentialsException exception) {
        log.warn(WARN_MSG + " {}", exception);
        ErrorRegisterResponseDto registerResponseDto = new ErrorRegisterResponseDto(WARN_MSG, HttpStatus.UNAUTHORIZED);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(registerResponseDto);
    }

}
