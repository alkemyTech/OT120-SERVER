package com.alkemy.ong.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UsernameAlreadyExistsException extends RuntimeException {

    public UsernameAlreadyExistsException(final String message) {
        super(message);
    }
}
