package com.apna_dukan_backend.cart.model.domain;

import java.util.UUID;

/**
 * Domain model for CartItem.
 * 
 * Represents a single item in a cart.
 * Rules:
 * - One CartItem per variant per cart
 * - quantity must be >= 1
 * - Stores ONLY variantId and quantity (no price/inventory)
 */
public class CartItem {
    private UUID cartItemId;
    private UUID cartId;
    private UUID variantId;
    private Integer quantity;

    public CartItem() {
    }

    public CartItem(UUID cartItemId, UUID cartId, UUID variantId, Integer quantity) {
        this.cartItemId = cartItemId;
        this.cartId = cartId;
        this.variantId = variantId;
        this.quantity = quantity;
    }

    // Getters and Setters
    public UUID getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(UUID cartItemId) {
        this.cartItemId = cartItemId;
    }

    public UUID getCartId() {
        return cartId;
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }

    public UUID getVariantId() {
        return variantId;
    }

    public void setVariantId(UUID variantId) {
        this.variantId = variantId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

