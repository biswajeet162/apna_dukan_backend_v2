package com.apna_dukan_backend.catalog.productgroup.dto;

import java.util.List;
import java.util.UUID;

public class ProductGroupAdminResponseDto {
    private UUID subCategoryId;
    private String subCategoryName;
    private List<ProductGroupAdminDto> productGroups;

    public ProductGroupAdminResponseDto() {
    }

    public ProductGroupAdminResponseDto(UUID subCategoryId, String subCategoryName, List<ProductGroupAdminDto> productGroups) {
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

    public List<ProductGroupAdminDto> getProductGroups() {
        return productGroups;
    }

    public void setProductGroups(List<ProductGroupAdminDto> productGroups) {
        this.productGroups = productGroups;
    }
}


