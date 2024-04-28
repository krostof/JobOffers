package com.example.joboffers.infrastructure.offersfetcher.controller;


import com.example.joboffers.domain.crud.OfferFacade;
import com.example.joboffers.domain.crud.dto.OfferRequestDto;
import com.example.joboffers.domain.crud.dto.OfferResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Log4j2
public class OfferRestController {

    private final OfferFacade offerFacade;

    @GetMapping("/offers")
    ResponseEntity<List<OfferResponseDto>> getOffers() {
        List<OfferResponseDto> allOffers = offerFacade.findAllOffers();
        return ResponseEntity.ok(allOffers);
    }

    @GetMapping("/offers/{id}")
    ResponseEntity<OfferResponseDto> getOffersById(@PathVariable String id) {
        OfferResponseDto offerResponseDto = offerFacade.findOfferById(id);
        return ResponseEntity.ok(offerResponseDto);
    }

    @PostMapping("/offers")
    ResponseEntity<SavedOfferResponseDto> createOffer(@RequestBody @Valid OfferRequestDto offerRequestDto) {
        OfferResponseDto offerResponseDto = offerFacade.saveOffer(offerRequestDto);
        log.info("Offer created: {}", offerResponseDto);
        SavedOfferResponseDto savedOfferResponseDto = new SavedOfferResponseDto("Offer was successfully created!", HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOfferResponseDto);
    }

}
