package com.example.joboffers.domain.crud;

import java.util.List;

class OfferDuplicateException extends RuntimeException{
    OfferDuplicateException(final String offerUrl) {
        super("Offer with URL: " + offerUrl + " exist!");
    }

    OfferDuplicateException(final String message, List<Offer> offers) {
        super("Error occurred during saving offers: " + message + offers.toString());
    }
}
