package com.apna_dukan_backend.catalog.layout.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class CatalogSection {
    private UUID sectionId;
    private SectionCode sectionCode;
    private String title;
    private String description;
    private LayoutType layoutType;
    private ScrollType scrollType;
    private int displayOrder;
    private boolean enabled;
    private boolean personalized;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CatalogSection() {
    }

    public CatalogSection(UUID sectionId, SectionCode sectionCode, String title, String description,
                         LayoutType layoutType, ScrollType scrollType, int displayOrder,
                         boolean enabled, boolean personalized, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.sectionId = sectionId;
        this.sectionCode = sectionCode;
        this.title = title;
        this.description = description;
        this.layoutType = layoutType;
        this.scrollType = scrollType;
        this.displayOrder = displayOrder;
        this.enabled = enabled;
        this.personalized = personalized;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public UUID getSectionId() {
        return sectionId;
    }

    public void setSectionId(UUID sectionId) {
        this.sectionId = sectionId;
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

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isPersonalized() {
        return personalized;
    }

    public void setPersonalized(boolean personalized) {
        this.personalized = personalized;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

