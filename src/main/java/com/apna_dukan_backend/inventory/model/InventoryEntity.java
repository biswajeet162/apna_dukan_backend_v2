package com.apna_dukan_backend.inventory.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "inventory")
@EntityListeners(AuditingEntityListener.class)
public class InventoryEntity {
    @Id
    @Column(name = "inventory_id", columnDefinition = "UUID")
    private UUID inventoryId;

    @Column(name = "variant_id", nullable = false, columnDefinition = "UUID", unique = true)
    private UUID variantId;

    @Column(name = "in_stock", nullable = false)
    private boolean inStock;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public InventoryEntity() {
    }

    public InventoryEntity(UUID inventoryId, UUID variantId, boolean inStock,
                          LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.inventoryId = inventoryId;
        this.variantId = variantId;
        this.inStock = inStock;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public UUID getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(UUID inventoryId) {
        this.inventoryId = inventoryId;
    }

    public UUID getVariantId() {
        return variantId;
    }

    public void setVariantId(UUID variantId) {
        this.variantId = variantId;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

