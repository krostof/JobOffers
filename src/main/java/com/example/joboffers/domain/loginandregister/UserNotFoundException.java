package com.example.joboffers.domain.loginandregister;

class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(final String message) {
        super(message);
    }
}
