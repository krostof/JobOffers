package com.example.joboffers.domain.crud;

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
    private String salary;
    private String offerUrl;


}
