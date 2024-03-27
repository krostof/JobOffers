package com.example.joboffers.domain.crud;



import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


class OfferRepositoryImplTest implements OfferRepository{
    Set<Offer> database = new HashSet<>();
    @Override
    public boolean existsByOfferUrl(final String offerUrl) {
        return database.stream()
                .anyMatch(offer -> offerUrl.equals(offer.getOfferUrl()));
    }

    @Override
    public Optional<Offer> findByOfferUrl(final String offerUrl) {
        return Optional.empty();
    }

    @Override
    public List<Offer> saveAll(final List<Offer> offers) {
        return offers.stream()
                .map(this::save)
                .toList();
    }

    @Override
    public List<Offer> findAll() {
        return database.stream().toList();
    }

    @Override
    public Optional<Offer> findById(final String id) {
        return Optional.of(database.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new OfferNotFoundException(id)));

    }

    @Override
    public Offer save(final Offer offer) {
        if (existsByOfferUrl(offer.getOfferUrl())){
            throw new OfferDuplicateException(offer.getOfferUrl());
        }
        UUID uuid = UUID.randomUUID();
        offer.setId(uuid.toString());
        database.add(offer);
        return offer;
    }
}
