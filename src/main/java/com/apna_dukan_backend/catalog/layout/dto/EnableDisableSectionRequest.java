package com.apna_dukan_backend.catalog.layout.dto;

import jakarta.validation.constraints.NotNull;

public class EnableDisableSectionRequest {
    @NotNull
    private Boolean enabled;

    public EnableDisableSectionRequest() {
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}

