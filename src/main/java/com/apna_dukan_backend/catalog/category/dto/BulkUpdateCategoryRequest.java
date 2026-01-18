package com.apna_dukan_backend.catalog.category.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class BulkUpdateCategoryRequest {
    @NotEmpty(message = "Categories list cannot be empty")
    @Valid
    private List<BulkUpdateCategoryItem> categories;

    public BulkUpdateCategoryRequest() {
    }

    public List<BulkUpdateCategoryItem> getCategories() {
        return categories;
    }

    public void setCategories(List<BulkUpdateCategoryItem> categories) {
        this.categories = categories;
    }
}

