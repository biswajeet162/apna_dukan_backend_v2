package com.apna_dukan_backend.catalog.productgroup.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public class BulkUpdateProductGroupItem {
    @NotNull(message = "ProductGroup ID is required")
    private UUID productGroupId;

    private String name;
    private String description;
    private String code;
    private Integer displayOrder;
    private Boolean enabled;
    private List<String> imageUrl;

    public BulkUpdateProductGroupItem() {
    }

    public UUID getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(UUID productGroupId) {
        this.productGroupId = productGroupId;
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






