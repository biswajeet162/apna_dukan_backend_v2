package com.apna_dukan_backend.cart.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

/**
 * JPA Entity for CartItem.
 * 
 * Table: cart_item
 * - cart_item_id (UUID, PK)
 * - cart_id (UUID, FK)
 * - variant_id (UUID)
 * - quantity (INT)
 * 
 * Unique constraint: (cart_id, variant_id)
 */
@Entity
@Table(name = "cart_item", uniqueConstraints = {
    @UniqueConstraint(name = "uk_cart_variant", columnNames = {"cart_id", "variant_id"})
})
@EntityListeners(AuditingEntityListener.class)
public class CartItemEntity {
    @Id
    @Column(name = "cart_item_id", columnDefinition = "UUID")
    private UUID cartItemId;

    @Column(name = "cart_id", nullable = false, columnDefinition = "UUID")
    private UUID cartId;

    @Column(name = "variant_id", nullable = false, columnDefinition = "UUID")
    private UUID variantId;

    @Column(nullable = false)
    private Integer quantity;

    public CartItemEntity() {
    }

    public CartItemEntity(UUID cartItemId, UUID cartId, UUID variantId, Integer quantity) {
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

