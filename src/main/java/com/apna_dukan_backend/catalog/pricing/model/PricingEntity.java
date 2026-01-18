package com.apna_dukan_backend.catalog.pricing.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pricing", indexes = {
    @Index(name = "idx_variant_active", columnList = "variant_id,active")
})
@EntityListeners(AuditingEntityListener.class)
public class PricingEntity {
    @Id
    @Column(name = "pricing_id", columnDefinition = "UUID")
    private UUID pricingId;

    @Column(name = "variant_id", nullable = false, columnDefinition = "UUID")
    private UUID variantId;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal mrp;

    @Column(name = "selling_price", nullable = false, precision = 19, scale = 2)
    private BigDecimal sellingPrice;

    @Column(name = "discount_percent", nullable = false)
    private Integer discountPercent;

    @Column(name = "valid_from")
    private LocalDateTime validFrom;

    @Column(name = "valid_to")
    private LocalDateTime validTo;

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

    public PricingEntity(UUID pricingId, UUID variantId, String currency, BigDecimal mrp,
                        BigDecimal sellingPrice, Integer discountPercent, LocalDateTime validFrom,
                        LocalDateTime validTo, boolean active, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.pricingId = pricingId;
        this.variantId = variantId;
        this.currency = currency;
        this.mrp = mrp;
        this.sellingPrice = sellingPrice;
        this.discountPercent = discountPercent;
        this.validFrom = validFrom;
        this.validTo = validTo;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getMrp() {
        return mrp;
    }

    public void setMrp(BigDecimal mrp) {
        this.mrp = mrp;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDateTime validTo) {
        this.validTo = validTo;
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

