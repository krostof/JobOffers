package joboffers.http.offers;


import com.example.joboffers.domain.crud.OfferFetchable;
import com.example.joboffers.infrastructure.offersfetcher.controller.http.OfferHttpClientConfig;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class OfferFetchableRestTemplateIntegrationTestConfig extends OfferHttpClientConfig {

    public OfferFetchable remoteOfferClient(int port, int connectionTimeout, int readTimeout){
        RestTemplate restTemplate = restTemplate(connectionTimeout,readTimeout,restTemplateResponseErrorHandler());
        return remoteOfferClient(restTemplate,"http://localhost",port);
    }

}
