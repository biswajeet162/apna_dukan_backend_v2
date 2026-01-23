package com.apna_dukan_backend.user.exception;

/**
 * Exception thrown when a user tries to access an address they don't own.
 */
public class AddressAccessDeniedException extends RuntimeException {
    public AddressAccessDeniedException(String message) {
        super(message);
    }
}

