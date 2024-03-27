package com.example.joboffers.domain.crud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
class Offer {

    private String id;
    private String companyName;
    private String position;
    private Double salary;
    private String offerUrl;


}
