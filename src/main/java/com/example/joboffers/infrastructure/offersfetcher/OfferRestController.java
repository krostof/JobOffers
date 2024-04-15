package com.example.joboffers.infrastructure.offersfetcher;

import com.example.joboffers.domain.crud.OfferFacade;
import com.example.joboffers.domain.crud.dto.OfferResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class OfferRestController {

    OfferFacade offerFacade;

    @GetMapping("/offers")
    ResponseEntity<List<OfferResponseDto>> getOffers() {
        List<OfferResponseDto> allOffers = offerFacade.findAllOffers();
        return ResponseEntity.ok(allOffers);
    }

}
