package com.example.joboffers.domain.crud;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Setter
@Getter
@Document
class Offer {
    @Id
    private String id;
    private String companyName;
    private String position;
    private String salary;
    private String offerUrl;

}
