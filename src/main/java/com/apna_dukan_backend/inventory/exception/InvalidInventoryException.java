package com.apna_dukan_backend.inventory.exception;

public class InvalidInventoryException extends RuntimeException {
    public InvalidInventoryException(String message) {
        super(message);
    }
}

