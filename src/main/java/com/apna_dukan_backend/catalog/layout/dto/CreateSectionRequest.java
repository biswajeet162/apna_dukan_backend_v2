package com.apna_dukan_backend.catalog.layout.dto;

import com.apna_dukan_backend.catalog.layout.model.LayoutType;
import com.apna_dukan_backend.catalog.layout.model.ScrollType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateSectionRequest {
    @NotBlank
    private String sectionCode;
    
    @NotNull
    private String title;
    
    private String description;
    
    @NotNull
    private LayoutType layoutType;
    
    @NotNull
    private ScrollType scrollType;
    
    @NotNull
    private Integer displayOrder;
    
    private Boolean enabled = true;
    
    private Boolean personalized = false;

    public CreateSectionRequest() {
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LayoutType getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(LayoutType layoutType) {
        this.layoutType = layoutType;
    }

    public ScrollType getScrollType() {
        return scrollType;
    }

    public void setScrollType(ScrollType scrollType) {
        this.scrollType = scrollType;
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

    public Boolean getPersonalized() {
        return personalized;
    }

    public void setPersonalized(Boolean personalized) {
        this.personalized = personalized;
    }
}

