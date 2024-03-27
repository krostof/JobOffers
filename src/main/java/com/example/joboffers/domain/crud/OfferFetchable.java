package com.example.joboffers.domain.crud;

import com.example.joboffers.domain.crud.dto.JobOfferResponseDto;

import java.util.List;

interface OfferFetchable {

    List<JobOfferResponseDto> fetchOffers();

}
