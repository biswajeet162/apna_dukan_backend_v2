package com.apna_dukan_backend.catalog.layout.application;

import com.apna_dukan_backend.catalog.layout.domain.LayoutType;
import com.apna_dukan_backend.catalog.layout.domain.ScrollType;
import com.apna_dukan_backend.catalog.layout.domain.SectionCode;

public class UpdateSectionRequest {
    private SectionCode sectionCode;
    private String title;
    private String description;
    private LayoutType layoutType;
    private ScrollType scrollType;
    private Integer displayOrder;
    private Boolean enabled;
    private Boolean personalized;

    public UpdateSectionRequest() {
    }

    public SectionCode getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(SectionCode sectionCode) {
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

