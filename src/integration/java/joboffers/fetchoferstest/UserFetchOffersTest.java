package joboffers.fetchoferstest;

import com.example.joboffers.domain.crud.OfferFetchable;
import com.example.joboffers.domain.crud.OfferRepository;
import com.example.joboffers.domain.crud.dto.OfferResponseDto;
import com.example.joboffers.infrastructure.dto.GetTokenResponseDto;
import com.example.joboffers.infrastructure.loginandregister.RegistrationResultDto;
import com.example.joboffers.infrastructure.scheduler.FetchOffersSchedulers;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.fasterxml.jackson.databind.ObjectMapper; // added missing import
import joboffers.BaseIntegrationTest;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
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
    @Autowired
    ObjectMapper objectMapper; // added missing object mapper

    @Test
    public void should_user_fetch_offers() throws Exception {
        // Step 1: No offers are available on the external HTTP server
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithZeroOffersJson())));

        // Step 2: First scheduler run, no offers added to the database
        List<OfferResponseDto> offerResponseDtos = fetchOffersSchedulers.updateJobOfferDatabase();
        assertThat(offerResponseDtos).isEmpty();

        // Step 3: User attempts to obtain JWT token with incorrect credentials - Unauthorized (401)
        ResultActions perform = mockMvc.perform(post("/token")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"username\": \"someUser\", \"password\": \"somePassword\" }"));

        perform.andExpect(status().isUnauthorized())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("Bad Credentials")));

        // Step 4: User makes GET /offers request without a JWT token - Unauthorized (401)
        ResultActions performGetOnOffers = mockMvc.perform(get("/offers")
                .contentType(MediaType.APPLICATION_JSON));

        performGetOnOffers.andExpect(status().isUnauthorized());

        // Step 5: User registers with POST /register - status OK (201)
        ResultActions performPostOnRegister = mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"username\": \"someUser\", \"password\": \"somePassword\" }"));

        performPostOnRegister.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username", is("someUser")))
                .andExpect(jsonPath("$.message", is("User registered successfully")));

        // Step 6: User obtains JWT token after registration - OK (200)
        ResultActions performPostOnToken = mockMvc.perform(post("/token")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"username\": \"someUser\", \"password\": \"somePassword\" }"));

        performPostOnToken.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.token", matchesPattern(Pattern.compile("^([A-Za-z0-9-_=]+\\.)+([A-Za-z0-9-_=])+\\.?$"))))
                .andExpect(jsonPath("$.message", is("Token was successfully granted!")));
        String contentAsString = performPostOnToken.andReturn().getResponse().getContentAsString();
        GetTokenResponseDto resultDto = objectMapper.readValue(contentAsString, GetTokenResponseDto.class);
        String token = resultDto.token();

        // Step 7: User makes GET /offers request with token, no offers available - OK (200)
        ResultActions performGetOffers = mockMvc.perform(get("/offers")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON));

        performGetOffers.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(0));

        // Step 8: Two new offers appear on the external HTTP server
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithTwoOffersJson())));

        // Step 9: Scheduler runs a second time, adding two offers with ids 1000 and 2000
        List<OfferResponseDto> twoNewOffers = fetchOffersSchedulers.updateJobOfferDatabase();
        assertThat(twoNewOffers).hasSize(2);

        // Step 10: User makes GET /offers with token - should return 2 offers
        ResultActions performGetForTwoOffers = mockMvc.perform(get("/offers")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        MvcResult performGetForTwoOffersMvcResult = performGetForTwoOffers.andExpect(status().isOk()).andReturn();
        String jsonWithTwoOffers = performGetForTwoOffersMvcResult.getResponse().getContentAsString();
        List<OfferResponseDto> twoOffers = objectMapper.readValue(jsonWithTwoOffers, new TypeReference<>() {
        });
        assertThat(twoOffers).hasSize(2);
        OfferResponseDto expectedFirstOffer = twoNewOffers.get(0);
        OfferResponseDto expectedSecondOffer = twoNewOffers.get(1);
        assertThat(twoOffers).containsExactlyInAnyOrder(
                new OfferResponseDto(expectedFirstOffer.id(), expectedFirstOffer.companyName(), expectedFirstOffer.position(), expectedFirstOffer.salary(), expectedFirstOffer.offerUrl()),
                new OfferResponseDto(expectedSecondOffer.id(), expectedSecondOffer.companyName(), expectedSecondOffer.position(), expectedSecondOffer.salary(), expectedSecondOffer.offerUrl())
        );

        // Step 11: User makes GET /offers/9999 - Not Found (404)
        ResultActions performGetOffersOnNonExistingId = mockMvc.perform(get("/offers/9999")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON));

        performGetOffersOnNonExistingId.andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("Offer with id: 9999 not found")))
                .andExpect(jsonPath("$.status", is("NOT_FOUND")));

        // Step 12: User makes GET /offers/1000 - OK (200)
        // TO-FIX
//        ResultActions performGetOffersOnExistingId = mockMvc.perform(get("/offers/1000")
//                .header("Authorization", "Bearer " + token)
//                .contentType(MediaType.APPLICATION_JSON));
//
//        performGetOffersOnExistingId.andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id", is("1000")));

        // Step 13: Another two offers appear on the external HTTP server
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithFourOffersJson())));

        // Step 14: Scheduler runs a third time, adding two more offers with ids 3000 and 4000
        offerResponseDtos = fetchOffersSchedulers.updateJobOfferDatabase();
        assertThat(offerResponseDtos).hasSize(2);

        // Step 15: User makes GET /offers with token - should return 4 offers
        ResultActions performGetOffersWithFourOffers = mockMvc.perform(get("/offers")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON));

        performGetOffersWithFourOffers.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(4));

        // Step 16: User makes POST /offers with token - Created (201)
        ResultActions performPostOfferRequest = mockMvc.perform(post("/offers")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                                {
                                  "companyName": "string",
                                  "position": "string",
                                  "salary": "string",
                                  "offerUrl": "string"
                                }
                        """.trim()));

        performPostOfferRequest.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("Offer was successfully created!")))
                .andExpect(jsonPath("$.status", is("CREATED")));
    }
}