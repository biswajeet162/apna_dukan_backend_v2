package com.apna_dukan_backend.catalog.category.dto;

import java.util.List;
import java.util.UUID;

public class CategorySectionResponseDto {
    private UUID sectionId;
    private String sectionCode;
    private List<CategoryDto> categories;

    public CategorySectionResponseDto() {
    }

    public CategorySectionResponseDto(UUID sectionId, String sectionCode, List<CategoryDto> categories) {
        this.sectionId = sectionId;
        this.sectionCode = sectionCode;
        this.categories = categories;
    }

    // Getters and Setters
    public UUID getSectionId() {
        return sectionId;
    }

    public void setSectionId(UUID sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }
}

