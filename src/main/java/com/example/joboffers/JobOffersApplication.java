package com.example.joboffers;

import com.example.joboffers.domain.crud.OfferFetchable;
import com.example.joboffers.domain.crud.dto.JobOfferResponseDto;
import com.example.joboffers.infrastructure.offersfetcher.OfferFetchableRestTemplate;
import jakarta.persistence.Access;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@Log4j2
@EnableScheduling
public class JobOffersApplication {

//    @Autowired
//    OfferFetchable offerFetchable;

    public static void main(String[] args) {
        SpringApplication.run(JobOffersApplication.class, args);
    }

//    @EventListener
//    public void handleContextRefresh(ContextRefreshedEvent event) {
//
//        List<JobOfferResponseDto> jobOfferResponseDtos = offerFetchable.fetchOffers();
//        log.info(jobOfferResponseDtos.toString());
//    }

}
