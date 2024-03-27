package com.example.joboffers.domain.crud;

import com.example.joboffers.domain.crud.dto.OfferRequestDto;
import com.example.joboffers.domain.crud.dto.OfferResponseDto;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
class OfferFacade {

    private final OfferRepository offerRepository;
    private final OfferService offerService;

    public List<OfferResponseDto> findAllOffers() {
        return offerRepository.findAll()
                .stream()
                .map(OfferMapper::mapOfferToOfferDto)
                .collect(Collectors.toList());
    }

    public List<OfferResponseDto> fetchAllOffersAndSaveAllIfNotExists() {
        return offerService.fetchAllOffersAndSaveAllIfNotExists()
                .stream()
                .map(OfferMapper::mapOfferToOfferDto)
                .toList();
    }

    public OfferResponseDto findOfferById(String id) {
        return offerRepository.findById(id)
                .map(OfferMapper::mapOfferToOfferDto)
                .orElseThrow(() -> new OfferNotFoundException(id));
    }

    public OfferResponseDto saveOffer(OfferRequestDto offerDto) {
        final Offer offer = OfferMapper.mapOfferDtoToOffer(offerDto);
        final Offer save = offerRepository.save(offer);
        return OfferMapper.mapOfferToOfferDto(save);
    }

}
