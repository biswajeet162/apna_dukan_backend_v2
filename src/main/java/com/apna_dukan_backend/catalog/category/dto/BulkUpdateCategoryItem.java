package com.apna_dukan_backend.catalog.category.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class BulkUpdateCategoryItem {
    @NotNull(message = "Category ID is required")
    private UUID categoryId;

    private String name;
    private String description;
    private String code;
    private Integer displayOrder;
    private Boolean enabled;

    public BulkUpdateCategoryItem() {
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

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}

