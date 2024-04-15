package com.example.joboffers;

import com.example.joboffers.domain.crud.OfferFetchable;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Log4j2
@EnableScheduling
@EnableMongoRepositories
public class JobOffersApplication {

    @Autowired
    OfferFetchable offerFetchable;

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
