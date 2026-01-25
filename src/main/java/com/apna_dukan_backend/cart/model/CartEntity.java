package com.apna_dukan_backend.cart.model;

import com.apna_dukan_backend.cart.model.domain.CartStatus;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * JPA Entity for Cart.
 * 
 * Table: cart
 * - cart_id (UUID, PK)
 * - user_id (UUID, UNIQUE where status = ACTIVE)
 * - status (VARCHAR)
 * - updated_at (TIMESTAMP)
 */
@Entity
@Table(name = "cart")
@EntityListeners(AuditingEntityListener.class)
public class CartEntity {
    @Id
    @Column(name = "cart_id", columnDefinition = "UUID")
    private UUID cartId;

    @Column(name = "user_id", nullable = false, columnDefinition = "UUID")
    private UUID userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CartStatus status;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public CartEntity() {
    }

    public CartEntity(UUID cartId, UUID userId, CartStatus status, LocalDateTime updatedAt) {
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

