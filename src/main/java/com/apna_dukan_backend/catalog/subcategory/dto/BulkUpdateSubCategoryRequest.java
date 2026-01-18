package com.apna_dukan_backend.catalog.subcategory.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class BulkUpdateSubCategoryRequest {
    @NotEmpty(message = "SubCategories list cannot be empty")
    @Valid
    private List<BulkUpdateSubCategoryItem> subCategories;

    public BulkUpdateSubCategoryRequest() {
    }

    public List<BulkUpdateSubCategoryItem> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<BulkUpdateSubCategoryItem> subCategories) {
        this.subCategories = subCategories;
    }
}


