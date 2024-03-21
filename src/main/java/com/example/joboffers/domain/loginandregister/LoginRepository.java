package com.example.joboffers.domain.loginandregister;

import java.util.Optional;

interface LoginRepository {

    Users save(Users users);

    Optional<Users> findUser(String name);
}
