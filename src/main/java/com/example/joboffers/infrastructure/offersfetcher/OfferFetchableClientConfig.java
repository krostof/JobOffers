package com.example.joboffers.infrastructure.offersfetcher;

import java.time.Duration;

import com.example.joboffers.domain.crud.OfferFetchable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OfferFetchableClientConfig {

    @Bean
    public RestTemplateResponseErrorHandler restTemplateResponseErrorHandler() {
        return new RestTemplateResponseErrorHandler();
    }

    @Bean
    public RestTemplate restTemplate(@Value("${offer.http.client.config.connectionTimeout}") long connectionTimeout,
                                     @Value("${offer.http.client.config.readTimeout}") long readTimeout,
                                     RestTemplateResponseErrorHandler restTemplateResponseErrorHandler) {
        return new RestTemplateBuilder()
                .errorHandler(restTemplateResponseErrorHandler)
                .setConnectTimeout(Duration.ofMillis(connectionTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .build();
    }

    @Bean
    public OfferFetchable remoteOfferClient(RestTemplate restTemplate,
                                            @Value("${offer.http.client.config.uri}") String uri,
                                            @Value("${offer.http.client.config.port}") int port) {
        return new OfferFetchableRestTemplate(restTemplate, uri, port);
    }
}
