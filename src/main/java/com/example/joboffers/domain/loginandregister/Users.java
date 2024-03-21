package com.example.joboffers.domain.loginandregister;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@Builder
class Users {

    private Long id;
    private String name;
    private String password;

}
