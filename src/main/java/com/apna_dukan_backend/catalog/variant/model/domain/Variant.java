package com.apna_dukan_backend.catalog.variant.model.domain;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class Variant {
    private UUID variantId;
    private UUID productId;
    private String sku;
    private String label;
    private Map<String, String> attributes;
    private boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Variant() {
    }

    public Variant(UUID variantId, UUID productId, String sku, String label,
                   Map<String, String> attributes, boolean enabled,
                   LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.variantId = variantId;
        this.productId = productId;
        this.sku = sku;
        this.label = label;
        this.attributes = attributes;
        this.enabled = enabled;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public UUID getVariantId() {
        return variantId;
    }

    public void setVariantId(UUID variantId) {
        this.variantId = variantId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
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





