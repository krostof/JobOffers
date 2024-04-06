package com.example.joboffers.infrastructure.offersfetcher;

public record JobOfferDto(
        String title,
        String company,
        String salary,
        String offerUrl
) {
}
