package com.example.joboffers.infrastructure.dto;

public record JobOfferDto(
        String title,
        String company,
        String salary,
        String offerUrl
) {
}
