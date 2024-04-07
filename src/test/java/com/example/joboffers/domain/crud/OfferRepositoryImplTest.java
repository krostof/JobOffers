package com.example.joboffers.domain.crud;



import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;


class OfferRepositoryImplTest implements OfferRepository{
    Set<Offer> database = new HashSet<>();
    @Override
    public boolean existsByOfferUrl(final String offerUrl) {
        return database.stream()
                .anyMatch(offer -> offerUrl.equals(offer.getOfferUrl()));
    }


    public Optional<Offer> findByOfferUrl(final String offerUrl) {
        return Optional.empty();
    }


    public List<Offer> saveAll(final List<Offer> offers) {
        return offers.stream()
                .map(this::save)
                .toList();
    }

    @Override
    public <S extends Offer> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public List<Offer> findAll() {
        return database.stream().toList();
    }

    @Override
    public List<Offer> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Offer entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Offer> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Optional<Offer> findById(final String id) {
        return Optional.of(database.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new OfferNotFoundException(id)));

    }

    @Override
    public boolean existsById(String s) {
        return false;
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

    @Override
    public <S extends Offer> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Offer> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends Offer> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Offer> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Offer> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Offer> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Offer> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Offer> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Offer, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Offer> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Offer> findAll(Pageable pageable) {
        return null;
    }
}
