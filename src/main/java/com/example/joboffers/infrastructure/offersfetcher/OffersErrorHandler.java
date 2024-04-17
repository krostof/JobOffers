package com.example.joboffers.infrastructure.offersfetcher;

import com.example.joboffers.domain.crud.OfferNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class OffersErrorHandler {

    @ExceptionHandler(OfferNotFoundException.class)
    public ResponseEntity<ErrorOfferResponseDto> handleException(OfferNotFoundException exception) {
        log.warn("SongNotFoundException while accessing song");
        ErrorOfferResponseDto errorSongResponseDto = new ErrorOfferResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorSongResponseDto);
    }
}
