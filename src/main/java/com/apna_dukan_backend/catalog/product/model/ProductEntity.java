package com.apna_dukan_backend.catalog.product.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class ProductEntity {
    @Id
    @Column(name = "product_id", columnDefinition = "UUID")
    private UUID productId;

    @Column(name = "product_group_id", nullable = false, columnDefinition = "UUID")
    private UUID productGroupId;

    @Column(nullable = false)
    private String name;

    private String brand;

    @Column(name = "primary_image_url")
    private String primaryImageUrl;

    @Column(name = "display_order", nullable = false)
    private int displayOrder;

    @Column(nullable = false)
    private boolean enabled;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public ProductEntity() {
    }

    public ProductEntity(UUID productId, UUID productGroupId, String name, String brand,
                        String primaryImageUrl, int displayOrder, boolean enabled,
                        LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.productId = productId;
        this.productGroupId = productGroupId;
        this.name = name;
        this.brand = brand;
        this.primaryImageUrl = primaryImageUrl;
        this.displayOrder = displayOrder;
        this.enabled = enabled;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public UUID getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(UUID productGroupId) {
        this.productGroupId = productGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrimaryImageUrl() {
        return primaryImageUrl;
    }

    public void setPrimaryImageUrl(String primaryImageUrl) {
        this.primaryImageUrl = primaryImageUrl;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

