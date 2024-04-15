package com.example.joboffers.infrastructure.controller;

import com.example.joboffers.infrastructure.dto.GetTokenRequestDto;
import com.example.joboffers.infrastructure.dto.GetTokenResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class LoginAndRegisterRestController {

    @PostMapping("/token")
    public ResponseEntity<GetTokenResponseDto> getToken(@RequestBody GetTokenRequestDto requestDto){
        GetTokenResponseDto dto = new GetTokenResponseDto(requestDto.username());
        log.info(requestDto.username());
        return ResponseEntity.ok(dto);
    }

}
