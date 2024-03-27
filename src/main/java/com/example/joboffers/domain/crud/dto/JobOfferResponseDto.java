package com.example.joboffers.domain.crud.dto;

import lombok.Builder;

@Builder
public record JobOfferResponseDto(
        String title,
        String company,
        Double salary,
        String offerUrl
) {
}
