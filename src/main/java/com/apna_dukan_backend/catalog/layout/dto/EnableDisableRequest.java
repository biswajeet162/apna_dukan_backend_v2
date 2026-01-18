package com.apna_dukan_backend.catalog.layout.dto;

import java.util.UUID;

public class EnableDisableRequest {
    private UUID sectionId;
    private Boolean enabled;

    public EnableDisableRequest() {
    }

    public UUID getSectionId() {
        return sectionId;
    }

    public void setSectionId(UUID sectionId) {
        this.sectionId = sectionId;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}

