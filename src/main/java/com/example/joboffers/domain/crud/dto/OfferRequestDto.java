package com.example.joboffers.domain.crud.dto;

import lombok.Builder;

@Builder
public record OfferRequestDto(String companyName,
                       String position,
                       Double salary,
                       String offerUrl) {
}
