package com.apna_dukan_backend.catalog.productgroup.exception;

public class ProductGroupNotFoundException extends RuntimeException {
    public ProductGroupNotFoundException(String message) {
        super(message);
    }
}

