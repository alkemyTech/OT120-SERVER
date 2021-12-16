package com.alkemy.ong.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class FirstNameInvalidException extends RuntimeException {

    public FirstNameInvalidException(final String message) {
        super(message);
    }
}
