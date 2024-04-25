package com.example.joboffers.infrastructure.offersfetcher.controller.error;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorPostOfferResponseDto(List<String> errors, HttpStatus status) {
}
