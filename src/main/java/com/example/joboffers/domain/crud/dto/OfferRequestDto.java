package com.example.joboffers.domain.crud.dto;

import lombok.Builder;

@Builder
public record OfferRequestDto(String companyName,
                       String position,
                              String salary,
                       String offerUrl) {
}
