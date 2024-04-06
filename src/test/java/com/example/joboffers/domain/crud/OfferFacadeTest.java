package com.example.joboffers.domain.crud;

import com.example.joboffers.domain.crud.dto.JobOfferResponseDto;
import com.example.joboffers.domain.crud.dto.OfferRequestDto;
import com.example.joboffers.domain.crud.dto.OfferResponseDto;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OfferFacadeTest {


    @Test
    public void should_fetch_from_jobs_from_remote_and_save_all_offers_when_repository_is_empty() {
        // given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration().offerFacadeForTests();
        assertThat(offerFacade.findAllOffers()).isEmpty();

        // when
        List<OfferResponseDto> result = offerFacade.fetchAllOffersAndSaveAllIfNotExists();

        // then
        assertThat(result).hasSize(5);
    }

    @Test
    public void should_save_only_2_offers_when_repository_had_4_added_with_offer_urls() {
        // given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration(
                List.of(
                        new JobOfferResponseDto("testTitle1", "testCompany1", "1234.", "testUrl1"),
                        new JobOfferResponseDto("testTitle2", "testCompany2", "2345.", "testUrl2"),
                        new JobOfferResponseDto("testTitle3", "testCompany3", "3456.", "testUrl3"),
                        new JobOfferResponseDto("testTitle4", "testCompany4", "8521.", "testUrl4"),
                        new JobOfferResponseDto("Junior", "Comarch", "1111.", "https://someurl.pl/5"),
                        new JobOfferResponseDto("Mid", "Finanteq", "2222.", "https://someother.pl/6")
                )
        ).offerFacadeForTests();
        offerFacade.saveOffer(new OfferRequestDto("companyName", "position", "1111.", "estUrl1"));
        offerFacade.saveOffer(new OfferRequestDto("companyName", "position", "1111.", "estUrl2"));
        offerFacade.saveOffer(new OfferRequestDto("companyName", "position", "1111.", "estUrl3"));
        offerFacade.saveOffer(new OfferRequestDto("companyName", "position", "1111.", "estUrl4"));
        assertThat(offerFacade.findAllOffers()).hasSize(4);

        // when
        List<OfferResponseDto> response = offerFacade.fetchAllOffersAndSaveAllIfNotExists();
        List<OfferResponseDto> results = response.stream()
                .filter(offerResponseDto -> offerResponseDto.offerUrl().contains("some"))
                .toList();

        // then

        assertThat(List.of(
                        results.get(0).offerUrl(),
                        results.get(1).offerUrl()
                )
        ).containsExactlyInAnyOrder("https://someurl.pl/5", "https://someother.pl/6");

    }

    @Test
    public void should_save_4_offers_when_there_are_no_offers_in_database() {
        // given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration(List.of()).offerFacadeForTests();

        // when
        offerFacade.saveOffer(new OfferRequestDto("companyName", "position", "1111.", "1"));
        offerFacade.saveOffer(new OfferRequestDto("companyName", "position", "1111.", "2"));
        offerFacade.saveOffer(new OfferRequestDto("companyName", "position", "1111.", "3"));
        offerFacade.saveOffer(new OfferRequestDto("companyName", "position", "1111.", "4"));

        // then
        assertThat(offerFacade.findAllOffers()).hasSize(4);
    }

    @Test
    public void should_find_offer_by_id_when_offer_was_saved() {
        // given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration(List.of()).offerFacadeForTests();
        OfferResponseDto offerResponseDto = offerFacade.saveOffer(new OfferRequestDto("companyName", "position", "1111.", "1"));
        // when
        OfferResponseDto offerById = offerFacade.findOfferById(offerResponseDto.id());

        // then
        assertThat(offerById).isEqualTo(OfferResponseDto.builder()
                .id(offerResponseDto.id())
                .companyName("companyName")
                .position("position")
                .salary("1111.")
                .offerUrl("1")
                .build()
        );
    }

    @Test
    public void should_throw_not_found_exception_when_offer_not_found() {
        // given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration(List.of()).offerFacadeForTests();
        assertThat(offerFacade.findAllOffers()).isEmpty();

        // when
        Exception exception = assertThrows(OfferNotFoundException.class, () -> offerFacade.findOfferById("1234"));

        String expectedMessage = "Offer with id: 1234 not found";
        String actualMessage = exception.getMessage();
        // then
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void should_throw_duplicate_key_exception_when_with_offer_url_exists() {
        // given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration(List.of()).offerFacadeForTests();
        OfferResponseDto offerResponseDto = offerFacade.saveOffer(new OfferRequestDto("id", "asds", "1111.", "hello.pl"));
        String savedId = offerResponseDto.id();
        assertThat(offerFacade.findOfferById(savedId).id()).isEqualTo(savedId);
        // when
        Throwable thrown = catchThrowable(() -> offerFacade.saveOffer(
                new OfferRequestDto("cx", "vc", "1111.", "hello.pl")));

        // then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(OfferDuplicateException.class)
                .hasMessage("Offer with URL: hello.pl exist!");
    }

}