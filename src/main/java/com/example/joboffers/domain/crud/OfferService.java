package com.example.joboffers.domain.crud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
class OfferService {

    private final OfferFetchable offerFetcher;
    private final OfferRepository offerRepository;

    List<Offer> fetchAllOffersAndSaveAllIfNotExists() {
        List<Offer> jobOffers = fetchOffers();
        final List<Offer> offers = filterNotExistingOffers(jobOffers);
        try {
            return offerRepository.saveAll(offers);
        } catch (OfferDuplicateException e) {
            throw new OfferDuplicateException(e.getMessage(), jobOffers);
        }
    }

    private List<Offer> filterNotExistingOffers(final List<Offer> jobOffers) {
        return jobOffers.stream()
                .filter(offer -> !offerRepository.existsByOfferUrl(offer.getOfferUrl()))
                .collect(Collectors.toList());
    }

    private List<Offer> fetchOffers() {
        return offerFetcher.fetchOffers()
                .stream()
                .map(OfferMapper::mapJobOfferResponseDtoToOffer)
                .toList();
    }

}
