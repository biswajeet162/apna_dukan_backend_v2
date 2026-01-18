package com.apna_dukan_backend.catalog.productgroup.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "product_group")
@EntityListeners(AuditingEntityListener.class)
public class ProductGroupEntity {
    @Id
    @Column(name = "product_group_id", columnDefinition = "UUID")
    private UUID productGroupId;

    @Column(name = "sub_category_id", nullable = false, columnDefinition = "UUID")
    private UUID subCategoryId;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private String code;

    @Column(name = "display_order", nullable = false)
    private int displayOrder;

    @Column(nullable = false)
    private boolean enabled;

    @Column(name = "image_urls", columnDefinition = "TEXT")
    private String imageUrlsJson;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Transient
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public ProductGroupEntity() {
    }

    public ProductGroupEntity(UUID productGroupId, UUID subCategoryId, String name, String description,
                             String code, int displayOrder, boolean enabled, List<String> imageUrls,
                             LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.productGroupId = productGroupId;
        this.subCategoryId = subCategoryId;
        this.name = name;
        this.description = description;
        this.code = code;
        this.displayOrder = displayOrder;
        this.enabled = enabled;
        this.setImageUrls(imageUrls);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public UUID getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(UUID productGroupId) {
        this.productGroupId = productGroupId;
    }

    public UUID getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(UUID subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getImageUrlsJson() {
        return imageUrlsJson;
    }

    public void setImageUrlsJson(String imageUrlsJson) {
        this.imageUrlsJson = imageUrlsJson;
    }

    public List<String> getImageUrls() {
        if (imageUrlsJson == null || imageUrlsJson.trim().isEmpty()) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(imageUrlsJson, new TypeReference<List<String>>() {});
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void setImageUrls(List<String> imageUrls) {
        if (imageUrls == null || imageUrls.isEmpty()) {
            this.imageUrlsJson = null;
        } else {
            try {
                this.imageUrlsJson = objectMapper.writeValueAsString(imageUrls);
            } catch (IOException e) {
                this.imageUrlsJson = null;
            }
        }
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


