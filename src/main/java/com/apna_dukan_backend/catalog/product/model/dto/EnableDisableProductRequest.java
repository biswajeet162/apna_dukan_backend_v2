package com.apna_dukan_backend.catalog.product.model.dto;

import jakarta.validation.constraints.NotNull;

public class EnableDisableProductRequest {
    @NotNull(message = "Enabled status is required")
    private Boolean enabled;

    public EnableDisableProductRequest() {
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}


