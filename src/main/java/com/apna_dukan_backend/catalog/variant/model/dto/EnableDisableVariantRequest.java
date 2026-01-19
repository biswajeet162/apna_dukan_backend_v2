package com.apna_dukan_backend.catalog.variant.model.dto;

import jakarta.validation.constraints.NotNull;

public class EnableDisableVariantRequest {
    @NotNull(message = "Enabled status is required")
    private Boolean enabled;

    public EnableDisableVariantRequest() {
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}





