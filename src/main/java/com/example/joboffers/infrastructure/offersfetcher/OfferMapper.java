package com.example.joboffers.infrastructure.offersfetcher;

import com.example.joboffers.domain.crud.dto.JobOfferResponseDto;
import com.example.joboffers.infrastructure.dto.JobOfferDto;

public class OfferMapper {
    public static JobOfferResponseDto mapJobOfferDtoToJobOfferResponseDto(JobOfferDto jobOfferDto) {
        return JobOfferResponseDto.builder()
                .title(jobOfferDto.title())
                .company(jobOfferDto.company())
                .salary(jobOfferDto.salary())
                .offerUrl(jobOfferDto.offerUrl())
                .build();
    }
}
