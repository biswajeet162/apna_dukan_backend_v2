package com.apna_dukan_backend.catalog.variant.exception;

public class VariantNotFoundException extends RuntimeException {
    public VariantNotFoundException(String message) {
        super(message);
    }
}

