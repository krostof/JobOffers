package com.example.joboffers.domain.crud;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface OfferRepository extends MongoRepository<Offer, String> {

    boolean existsByOfferUrl(String offerUrl);

}
