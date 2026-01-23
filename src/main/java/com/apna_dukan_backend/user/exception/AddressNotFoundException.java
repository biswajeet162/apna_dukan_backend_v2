package com.apna_dukan_backend.user.exception;

/**
 * Exception thrown when an address is not found.
 */
public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(String message) {
        super(message);
    }
}

