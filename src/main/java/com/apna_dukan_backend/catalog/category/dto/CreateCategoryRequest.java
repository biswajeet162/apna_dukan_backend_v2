package com.apna_dukan_backend.catalog.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class CreateCategoryRequest {
    @NotNull(message = "Section ID is required")
    private UUID sectionId;

    @NotBlank(message = "Category name is required")
    private String name;

    private String description;

    @NotBlank(message = "Category code is required")
    private String code;

    @NotNull(message = "Display order is required")
    private Integer displayOrder;

    private Boolean enabled = true;

    public CreateCategoryRequest() {
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

