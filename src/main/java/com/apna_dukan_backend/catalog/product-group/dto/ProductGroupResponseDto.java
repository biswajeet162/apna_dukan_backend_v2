package com.apna_dukan_backend.catalog.productgroup.dto;

import java.util.List;
import java.util.UUID;

public class ProductGroupResponseDto {
    private UUID subCategoryId;
    private String subCategoryName;
    private List<ProductGroupDto> productGroups;

    public ProductGroupResponseDto() {
    }

    public ProductGroupResponseDto(UUID subCategoryId, String subCategoryName, List<ProductGroupDto> productGroups) {
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
        this.productGroups = productGroups;
    }

    // Getters and Setters
    public UUID getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(UUID subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public List<ProductGroupDto> getProductGroups() {
        return productGroups;
    }

    public void setProductGroups(List<ProductGroupDto> productGroups) {
        this.productGroups = productGroups;
    }
}


