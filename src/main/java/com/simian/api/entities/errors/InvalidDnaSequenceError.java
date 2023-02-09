package com.simian.api.entities.errors;

public class InvalidDnaSequenceError extends RuntimeException {

    public InvalidDnaSequenceError(String message) {
        super(message);
    }

}
