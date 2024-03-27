package com.example.joboffers.domain.crud;

import com.example.joboffers.domain.crud.dto.JobOfferResponseDto;


import java.util.List;

public class FetcherTestImpl implements OfferFetchable {

    List<JobOfferResponseDto> listOfOffers;

    FetcherTestImpl(List<JobOfferResponseDto> listOfOffers) {
        this.listOfOffers = listOfOffers;
    }

    @Override
    public List<JobOfferResponseDto> fetchOffers() {
        return listOfOffers;
    }
}
