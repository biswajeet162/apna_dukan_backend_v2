package com.apna_dukan_backend.catalog.productgroup.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public class CreateProductGroupRequest {
    @NotNull(message = "SubCategory ID is required")
    private UUID subCategoryId;

    @NotBlank(message = "ProductGroup name is required")
    private String name;

    private String description;

    @NotBlank(message = "ProductGroup code is required")
    private String code;

    @NotNull(message = "Display order is required")
    private Integer displayOrder;

    private Boolean enabled = true;

    private List<String> imageUrl;

    public CreateProductGroupRequest() {
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

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }
}


