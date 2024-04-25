package com.example.joboffers.infrastructure.offersfetcher.controller;

import org.springframework.http.HttpStatus;

public record SavedOfferResponseDto(String message, HttpStatus status) {
}
