package com.apna_dukan_backend.catalog.product.model.dto;

import java.util.UUID;

public class VariantSummaryDto {
    private UUID variantId;
    private String label;

    public VariantSummaryDto() {
    }

    public VariantSummaryDto(UUID variantId, String label) {
        this.variantId = variantId;
        this.label = label;
    }

    public UUID getVariantId() {
        return variantId;
    }

    public void setVariantId(UUID variantId) {
        this.variantId = variantId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

