package com.example.joboffers.domain.crud;

import com.example.joboffers.domain.crud.dto.JobOfferResponseDto;
import com.example.joboffers.domain.crud.dto.OfferRequestDto;
import com.example.joboffers.domain.crud.dto.OfferResponseDto;

class OfferMapper {

    public static OfferResponseDto mapOfferToOfferDto(Offer offer) {
        return OfferResponseDto.builder()
                .id(offer.getId())
                .companyName(offer.getCompanyName())
                .position(offer.getPosition())
                .salary(offer.getSalary())
                .offerUrl(offer.getOfferUrl())
                .build();
    }

    public static Offer mapOfferDtoToOffer(OfferRequestDto dto) {
        return Offer.builder()
                .companyName(dto.companyName())
                .position(dto.position())
                .salary(dto.salary())
                .offerUrl(dto.offerUrl())
                .build();
    }

    public static Offer mapJobOfferResponseDtoToOffer(JobOfferResponseDto dto) {
        return Offer.builder()
                .offerUrl(dto.offerUrl())
                .salary(dto.salary())
                .position(dto.title())
                .companyName(dto.company())
                .build();
    }

}
