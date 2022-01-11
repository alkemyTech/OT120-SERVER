package com.alkemy.ong.exception;

public class NotEmptyException extends RuntimeException {

    public NotEmptyException(String error) {
        super(error);
    }
}
