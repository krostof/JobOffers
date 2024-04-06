package com.example.joboffers.domain.crud;

import com.example.joboffers.domain.crud.dto.JobOfferResponseDto;

import java.util.List;

public interface OfferFetchable {

    List<JobOfferResponseDto> fetchOffers();

}
