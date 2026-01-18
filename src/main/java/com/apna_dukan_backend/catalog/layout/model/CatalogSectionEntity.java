package com.apna_dukan_backend.catalog.layout.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "catalog_section")
@EntityListeners(AuditingEntityListener.class)
public class CatalogSectionEntity {
    @Id
    @Column(columnDefinition = "UUID")
    private UUID sectionId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SectionCode sectionCode;

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LayoutType layoutType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScrollType scrollType;

    @Column(nullable = false)
    private int displayOrder;

    @Column(nullable = false)
    private boolean enabled;

    @Column(nullable = false)
    private boolean personalized;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public CatalogSectionEntity() {
    }

    public CatalogSectionEntity(UUID sectionId, SectionCode sectionCode, String title, String description,
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

