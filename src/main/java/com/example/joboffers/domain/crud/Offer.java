package com.example.joboffers.domain.crud;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Builder
@Setter
@Getter
@Document("offers")
class Offer {
    @Id
    private String id;
    @Field("company")
    String companyName;
    @Field("position")
    String position;
    @Field("salary")
    String salary;
    @Field("url")
    @Indexed(unique = true)
    String offerUrl;

}
