package com.example.joboffers.domain.crud;

public class OfferNotFoundException extends RuntimeException
{
     OfferNotFoundException(final String id) {
        super("Offer with id: " + id + " not found");
    }
}
