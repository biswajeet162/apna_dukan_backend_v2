package com.apna_dukan_backend.catalog.pricing.model.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Pricing {
    private UUID pricingId;
    private UUID variantId;
    private String currency;
    private BigDecimal mrp;
    private BigDecimal sellingPrice;
    private Integer discountPercent;
    private LocalDateTime validFrom;
    private LocalDateTime validTo;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Pricing() {
    }

    public Pricing(UUID pricingId, UUID variantId, String currency, BigDecimal mrp,
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


