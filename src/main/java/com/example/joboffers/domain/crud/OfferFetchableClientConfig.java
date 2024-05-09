package com.example.joboffers.domain.crud;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;


@Configuration
public class OfferFetchableClientConfig {

    @Bean
    OfferFacade offerFacade(OfferFetchable offerFetchable, OfferRepository repository) {
        OfferService offerService = new OfferService(offerFetchable, repository);
        return new OfferFacade(repository, offerService);
    }
}
