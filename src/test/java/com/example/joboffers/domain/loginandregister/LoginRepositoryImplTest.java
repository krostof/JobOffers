package com.example.joboffers.domain.loginandregister;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

class LoginRepositoryImplTest implements LoginRepository {

    Set<Users> usersSet = new HashSet<>();


    @Override
    public Users save(final Users users) {
        usersSet.add(users);
        return users;
    }

    @Override
    public Optional<Users> findUser(final String name) {
        return Optional.of(usersSet.stream()
                .filter(user -> user.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("TEST")));
    }
}
