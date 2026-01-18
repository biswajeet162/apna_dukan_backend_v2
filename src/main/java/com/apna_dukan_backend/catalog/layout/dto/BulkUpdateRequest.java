package com.apna_dukan_backend.catalog.layout.dto;

import java.util.List;
import java.util.UUID;

public class BulkUpdateRequest {
    private List<UUID> sectionIds;
    private Boolean enabled;
    private Boolean personalized;
    private Integer displayOrder;

    public BulkUpdateRequest() {
    }

    public List<UUID> getSectionIds() {
        return sectionIds;
    }

    public void setSectionIds(List<UUID> sectionIds) {
        this.sectionIds = sectionIds;
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

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
}

