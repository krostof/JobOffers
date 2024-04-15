package com.example.joboffers.infrastructure.scheduler;

import com.example.joboffers.domain.crud.OfferFacade;
import com.example.joboffers.domain.crud.dto.OfferResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@AllArgsConstructor
@Log4j2
public class FetchOffersSchedulers {

    private final OfferFacade offerFacade;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    @Scheduled(cron = "${http.offers.scheduler.request.delay}")
    public List<OfferResponseDto> updateJobOfferDatabase(){
        log.info("Started offers fetching {}",simpleDateFormat.format(new Date()));
        return offerFacade.fetchAllOffersAndSaveAllIfNotExists();
    }

}
