package com.example.joboffers.infrastructure.offersfetcher;

import org.springframework.http.HttpStatus;

public record ErrorOfferResponseDto(String message, HttpStatus status) {
}
