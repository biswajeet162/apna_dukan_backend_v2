package com.apna_dukan_backend.catalog.category.dto;

import java.util.UUID;

public class CategoryDto {
    private UUID categoryId;
    private UUID sectionId;
    private String name;
    private String description;
    private String code;
    private int displayOrder;
    private boolean enabled;

    public CategoryDto() {
    }

    public CategoryDto(UUID categoryId, UUID sectionId, String name, String description,
                      String code, int displayOrder, boolean enabled) {
        this.categoryId = categoryId;
        this.sectionId = sectionId;
        this.name = name;
        this.description = description;
        this.code = code;
        this.displayOrder = displayOrder;
        this.enabled = enabled;
    }

    // Getters and Setters
    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public UUID getSectionId() {
        return sectionId;
    }

    public void setSectionId(UUID sectionId) {
        this.sectionId = sectionId;
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
}

