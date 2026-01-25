package com.apna_dukan_backend.cart.model.domain;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain model for Cart.
 * 
 * Represents a user's shopping cart.
 * Rules:
 * - Only ONE ACTIVE cart per user
 * - Cart stores ONLY user intent (variantId, quantity)
 * - Does NOT store price or inventory
 */
public class Cart {
    private UUID cartId;
    private UUID userId;
    private CartStatus status;
    private LocalDateTime updatedAt;

    public Cart() {
    }

    public Cart(UUID cartId, UUID userId, CartStatus status, LocalDateTime updatedAt) {
        this.cartId = cartId;
        this.userId = userId;
        this.status = status;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public UUID getCartId() {
        return cartId;
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public CartStatus getStatus() {
        return status;
    }

    public void setStatus(CartStatus status) {
        this.status = status;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

