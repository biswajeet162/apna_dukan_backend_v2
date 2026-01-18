package com.apna_dukan_backend.catalog.product.exception;

public class DuplicateProductCodeException extends RuntimeException {
    public DuplicateProductCodeException(String message) {
        super(message);
    }
}


