package com.example.joboffers.infrastructure.offersfetcher.controller.http;

import com.example.joboffers.domain.crud.OfferFetchable;
import com.example.joboffers.domain.crud.dto.JobOfferResponseDto;
import com.example.joboffers.infrastructure.dto.JobOfferDto;
import com.example.joboffers.infrastructure.offersfetcher.OfferMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.web.client.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Log4j2
public class OfferFetchableRestTemplate implements OfferFetchable {

    private final RestTemplate restTemplate;
    private final String uri;
    private final int port;

    @Override
    public List<JobOfferResponseDto> fetchOffers() {
        try {
            log.info("Fetching offers");
            String url = uri + ":" + port + "/offers";
            url = UriComponentsBuilder.fromHttpUrl(url).toUriString();

            HttpHeaders headers = new HttpHeaders();
            final HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<JobOfferDto[]> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntity,
                    JobOfferDto[].class);

            JobOfferDto[] jobOffers = responseEntity.getBody();

            if (jobOffers.length == 0) {
                log.info("Response is empty");
                return Collections.emptyList();
            }

            return convertToResponse(jobOffers);
        } catch (Exception e) {
            ResponseStatusException responseException = new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            String errorMsg = e instanceof RestClientException ? "Error while fetching offers using http client: "
                    : "Unexpected error while fetching offers using http client: ";
            log.error(errorMsg + e.getMessage());
            throw responseException;
        }
    }

    private List<JobOfferResponseDto> convertToResponse(JobOfferDto[] jobOffers){
        return Arrays.stream(jobOffers)
                .map(OfferMapper::mapJobOfferDtoToJobOfferResponseDto)
                .collect(Collectors.toList());
    }
}