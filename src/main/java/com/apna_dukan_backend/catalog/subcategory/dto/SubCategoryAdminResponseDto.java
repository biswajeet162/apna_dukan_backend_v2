package com.apna_dukan_backend.catalog.subcategory.dto;

import java.util.List;
import java.util.UUID;

public class SubCategoryAdminResponseDto {
    private UUID categoryId;
    private String categoryName;
    private List<SubCategoryAdminDto> subCategories;

    public SubCategoryAdminResponseDto() {
    }

    public SubCategoryAdminResponseDto(UUID categoryId, String categoryName, List<SubCategoryAdminDto> subCategories) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.subCategories = subCategories;
    }

    // Getters and Setters
    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<SubCategoryAdminDto> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategoryAdminDto> subCategories) {
        this.subCategories = subCategories;
    }
}

