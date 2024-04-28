package com.example.joboffers.domain.loginandregister;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

class LoginRepositoryImplTest implements LoginRepository {

    Set<Users> usersSet = new HashSet<>();


    @Override
    public Users save(final Users users) {
        usersSet.add(users);
        return users;
    }

    @Override
    public Optional<Users> findUsersByUsername(String username) {
        return Optional.of(usersSet.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("TEST")));

    }

    @Override
    public <S extends Users> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Users> List<S> insert(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends Users> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Users> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Users> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Users> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Users> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Users> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Users, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Users> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<Users> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<Users> findAll() {
        return List.of();
    }

    @Override
    public List<Users> findAllById(Iterable<String> strings) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Users entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Users> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Users> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Users> findAll(Pageable pageable) {
        return null;
    }


}
