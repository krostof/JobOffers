package com.example.joboffers.domain.loginandregister;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface LoginRepository extends MongoRepository<Users, String> {

    Optional<Users> findUsersByUsername(String username);
}
