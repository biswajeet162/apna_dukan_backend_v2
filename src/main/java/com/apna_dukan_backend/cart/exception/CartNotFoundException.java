package com.apna_dukan_backend.cart.exception;

/**
 * Exception thrown when a cart is not found.
 */
public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String message) {
        super(message);
    }
}

