package com.unifap.biblioteca.exceptions;

import lombok.Getter;

@Getter
public class EntityWithErrorsException extends Exception {

    private static final long serialVersionUID = 1L;

    private final String property;

    // Exception constructor
    public EntityWithErrorsException(String property, String message) {
        super(message);
        this.property = property;
    }
}