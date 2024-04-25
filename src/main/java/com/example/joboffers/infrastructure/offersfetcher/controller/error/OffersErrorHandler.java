package com.example.joboffers.infrastructure.offersfetcher.controller.error;


import com.example.joboffers.domain.crud.OfferNotFoundException;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
@Log4j2
class OffersErrorHandler {

    @ExceptionHandler(OfferNotFoundException.class)
    public ResponseEntity<ErrorOfferResponseDto> offerNotFoundException(OfferNotFoundException exception) {
        log.warn("OfferNotFoundException while accessing offers");
        ErrorOfferResponseDto offerResponseDto = new ErrorOfferResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(offerResponseDto);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ErrorPostOfferResponseDto> offerDuplicatedException(DuplicateKeyException exception) {
        log.warn("OfferDuplicateException while posting offer");
        ErrorPostOfferResponseDto offerResponseDto = new ErrorPostOfferResponseDto(Collections.singletonList(exception.getMessage()), HttpStatus.CONFLICT);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(offerResponseDto);
    }
}
