package com.apna_dukan_backend.cart.model.domain;

/**
 * Cart status enumeration.
 * 
 * ACTIVE: Cart is currently active and can be modified
 * CHECKED_OUT: Cart has been converted to an order (future use)
 */
public enum CartStatus {
    ACTIVE,
    CHECKED_OUT
}

