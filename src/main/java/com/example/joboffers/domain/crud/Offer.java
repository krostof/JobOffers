package com.example.joboffers.domain.crud;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@Setter
@Getter
class Offer {
    private String id;
    private String companyName;
    private String position;
    private String salary;
    private String offerUrl;

}
