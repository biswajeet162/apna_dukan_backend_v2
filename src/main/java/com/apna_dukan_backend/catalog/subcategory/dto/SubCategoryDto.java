package com.apna_dukan_backend.catalog.subcategory.dto;

import java.util.List;
import java.util.UUID;

public class SubCategoryDto {
    private UUID subCategoryId;
    private UUID categoryId;
    private String name;
    private String description;
    private String code;
    private int displayOrder;
    private boolean enabled;
    private List<String> imageUrl;

    public SubCategoryDto() {
    }

    public SubCategoryDto(UUID subCategoryId, UUID categoryId, String name, String description,
                         String code, int displayOrder, boolean enabled, List<String> imageUrl) {
        this.subCategoryId = subCategoryId;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.code = code;
        this.displayOrder = displayOrder;
        this.enabled = enabled;
        this.imageUrl = imageUrl;
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
}

