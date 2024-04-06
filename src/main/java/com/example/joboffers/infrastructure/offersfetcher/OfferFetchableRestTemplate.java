package com.example.joboffers.infrastructure.offersfetcher;

import com.example.joboffers.domain.crud.OfferFetchable;
import com.example.joboffers.domain.crud.dto.JobOfferResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
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
            String urlForService = getUri();
            final String url = UriComponentsBuilder.fromHttpUrl(urlForService).toUriString();
            JobOfferDto[] jobOffers = restTemplate.getForObject(url, JobOfferDto[].class);
            if (jobOffers == null || jobOffers.length == 0) {
                log.info("Response is empty");
                return Collections.emptyList();
            }
            return Arrays.stream(jobOffers)
                    .map(OfferMapper::mapJobOfferDtoToJobOfferResponseDto)
                    .collect(Collectors.toList());
        } catch (ResourceAccessException e) {
            log.error("Error while fetching offers using http client: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String getUri() {
        return uri + ":" + port + "/offers";
    }
}
