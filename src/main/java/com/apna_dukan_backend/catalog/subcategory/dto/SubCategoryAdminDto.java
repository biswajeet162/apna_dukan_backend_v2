package com.apna_dukan_backend.catalog.subcategory.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class SubCategoryAdminDto {
    private UUID subCategoryId;
    private UUID categoryId;
    private String name;
    private String description;
    private String code;
    private int displayOrder;
    private boolean enabled;
    private List<String> imageUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime updatedAt;

    public SubCategoryAdminDto() {
    }

    public SubCategoryAdminDto(UUID subCategoryId, UUID categoryId, String name, String description,
                              String code, int displayOrder, boolean enabled, List<String> imageUrl,
                              LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.subCategoryId = subCategoryId;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.code = code;
        this.displayOrder = displayOrder;
        this.enabled = enabled;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public UUID getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(UUID subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
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

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
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


