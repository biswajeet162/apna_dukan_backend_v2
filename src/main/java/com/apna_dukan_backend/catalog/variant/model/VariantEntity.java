package com.apna_dukan_backend.catalog.variant.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "variant")
@EntityListeners(AuditingEntityListener.class)
public class VariantEntity {
    @Id
    @Column(name = "variant_id", columnDefinition = "UUID")
    private UUID variantId;

    @Column(name = "product_id", nullable = false, columnDefinition = "UUID")
    private UUID productId;

    @Column(nullable = false, unique = true)
    private String sku;

    @Column(nullable = false)
    private String label;

    @Column(name = "attributes", columnDefinition = "TEXT")
    private String attributesJson;

    @Column(nullable = false)
    private boolean enabled;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Transient
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public VariantEntity() {
    }

    public VariantEntity(UUID variantId, UUID productId, String sku, String label,
                        Map<String, String> attributes, boolean enabled,
                        LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.variantId = variantId;
        this.productId = productId;
        this.sku = sku;
        this.label = label;
        this.setAttributes(attributes);
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

    public String getAttributesJson() {
        return attributesJson;
    }

    public void setAttributesJson(String attributesJson) {
        this.attributesJson = attributesJson;
    }

    public Map<String, String> getAttributes() {
        if (attributesJson == null || attributesJson.trim().isEmpty()) {
            return new HashMap<>();
        }
        try {
            return objectMapper.readValue(attributesJson, new TypeReference<Map<String, String>>() {});
        } catch (IOException e) {
            return new HashMap<>();
        }
    }

    public void setAttributes(Map<String, String> attributes) {
        if (attributes == null || attributes.isEmpty()) {
            this.attributesJson = null;
        } else {
            try {
                this.attributesJson = objectMapper.writeValueAsString(attributes);
            } catch (IOException e) {
                this.attributesJson = null;
            }
        }
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

