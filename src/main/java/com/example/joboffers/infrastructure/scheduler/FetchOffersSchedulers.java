package com.example.joboffers.infrastructure.scheduler;

import com.example.joboffers.domain.crud.OfferFacade;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Log4j2
public class FetchOffersSchedulers {

    private final OfferFacade offerFacade;

    @Scheduled(cron = "* */1 * * * *")
    public void f(){
        log.info("TEST");
    }

}
