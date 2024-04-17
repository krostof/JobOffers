package joboffers;

import com.example.joboffers.domain.crud.OfferFetchable;
import com.example.joboffers.domain.crud.OfferRepository;
import com.example.joboffers.domain.crud.dto.OfferResponseDto;
import com.example.joboffers.infrastructure.scheduler.FetchOffersSchedulers;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;


import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Log4j2
public class UserFetchOffersTest extends BaseIntegrationTest implements SampleJobOffersResponse {

    @Autowired
    OfferFetchable offerFetchable;
    @Autowired
    FetchOffersSchedulers fetchOffersSchedulers;
    @Autowired
    OfferRepository offerRepository;

    @Test
    public void should_user_fetch_offers() throws Exception {
//        step 1: there are no offers in external HTTP server (http://ec2-3-120-147-150.eu-central-1.compute.amazonaws.com:5057/offers)
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithZeroOffers())));
//        step 2: scheduler ran 1st time and made GET to external server and system added 0 offers to database
        List<OfferResponseDto> offerResponseDtos = fetchOffersSchedulers.updateJobOfferDatabase();
        assertThat(offerResponseDtos.isEmpty());
//        step 3: user tried to get JWT token by requesting POST /token with username=someUser, password=somePassword and system returned UNAUTHORIZED(401)

        ResultActions perform = mockMvc.perform(post("/token")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"username\": \"someUser\", \"password\": \"somePassword\" }"));

        perform.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username", is("someUser")));

//        step 4: user made GET /offers with no jwt token and system returned UNAUTHORIZED(401)
//        step 5: user made POST /register with username=someUser, password=somePassword and system registered user with status OK(200)
//        step 6: user tried to get JWT token by requesting POST /token with username=someUser, password=somePassword and system returned OK(200) and jwttoken=AAAA.BBBB.CCC
//        step 7: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 0 offers
        ResultActions performGetOffers = mockMvc.perform(get("/offers")
                .contentType(MediaType.APPLICATION_JSON));

        performGetOffers.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(0));

//        step 8: there are 2 new offers in external HTTP server
//        step 9: scheduler ran 2nd time and made GET to external server and system added 2 new offers with ids: 1000 and 2000 to database
//        step 10: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 2 offers with ids: 1000 and 2000
//        step 11: user made GET /offers/9999 and system returned NOT_FOUND(404) with message “Offer with id 9999 not found”
        ResultActions performGetOffersOnNonExistingId = mockMvc.perform(get("/offers/9999")
                .contentType(MediaType.APPLICATION_JSON));

        performGetOffersOnNonExistingId.andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message",is("Offer with id: 9999 not found")))
                .andExpect(jsonPath("$.status",is("NOT_FOUND")));
//        step 12: user made GET /offers/1000 and system returned OK(200) with offer
//        step 13: there are 2 new offers in external HTTP server
//        step 14: scheduler ran 3rd time and made GET to external server and system added 2 new offers with ids: 3000 and 4000 to database
//        step 15: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 4 offers with ids: 1000,2000, 3000 and 4000

    }


}
