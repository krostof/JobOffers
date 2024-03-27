package com.example.joboffers.domain.crud;

import com.example.joboffers.domain.crud.dto.JobOfferResponseDto;


import java.util.List;

public class OfferFacadeTestConfiguration {

    private final FetcherTestImpl fetcherTest;
    private final OfferRepositoryImplTest offerRepository;

    OfferFacadeTestConfiguration() {
        this.fetcherTest = new FetcherTestImpl(
                List.of(
                        new JobOfferResponseDto("testTitle1", "testCompany1", 1234., "testUrl1"),
                        new JobOfferResponseDto("testTitle2", "testCompany2", 2345., "testUrl2"),
                        new JobOfferResponseDto("testTitle3", "testCompany3", 3456., "testUrl3"),
                        new JobOfferResponseDto("testTitle4", "testCompany4", 8521., "testUrl4"),
                        new JobOfferResponseDto("testTitle5", "testCompany5", 3698., "testUrl5")

                )
        );
        this.offerRepository = new OfferRepositoryImplTest();
    }

    OfferFacadeTestConfiguration(List<JobOfferResponseDto> remoteClientOffers) {
        this.fetcherTest = new FetcherTestImpl(remoteClientOffers);
        this.offerRepository = new OfferRepositoryImplTest();
    }

    OfferFacade offerFacadeForTests() {
        return new OfferFacade(offerRepository, new OfferService(fetcherTest, offerRepository));
    }
}
