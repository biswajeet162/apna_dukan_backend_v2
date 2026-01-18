package com.apna_dukan_backend.pricing.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pricing")
@EntityListeners(AuditingEntityListener.class)
public class PricingEntity {
    @Id
    @Column(name = "pricing_id", columnDefinition = "UUID")
    private UUID pricingId;

    @Column(name = "variant_id", nullable = false, columnDefinition = "UUID")
    private UUID variantId;

    @Column(name = "selling_price", nullable = false)
    private double sellingPrice;

    @Column(nullable = false)
    private double mrp;

    @Column(name = "discount_percent")
    private int discountPercent;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private boolean active;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public PricingEntity() {
    }

    public PricingEntity(UUID pricingId, UUID variantId, double sellingPrice, double mrp,
                        int discountPercent, String currency, boolean active,
                        LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.pricingId = pricingId;
        this.variantId = variantId;
        this.sellingPrice = sellingPrice;
        this.mrp = mrp;
        this.discountPercent = discountPercent;
        this.currency = currency;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public UUID getPricingId() {
        return pricingId;
    }

    public void setPricingId(UUID pricingId) {
        this.pricingId = pricingId;
    }

    public UUID getVariantId() {
        return variantId;
    }

    public void setVariantId(UUID variantId) {
        this.variantId = variantId;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getMrp() {
        return mrp;
    }

    public void setMrp(double mrp) {
        this.mrp = mrp;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

