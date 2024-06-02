package com.example.joboffers.domain.crud.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record OfferResponseDto(String id,
                        String companyName,
                        String position,
                               String salary,
                        String offerUrl) implements Serializable {
}
