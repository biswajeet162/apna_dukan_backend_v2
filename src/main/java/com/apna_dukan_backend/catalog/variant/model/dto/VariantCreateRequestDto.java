package com.apna_dukan_backend.catalog.variant.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.Map;

public class VariantCreateRequestDto {
    @NotBlank(message = "SKU is required")
    @Schema(description = "Unique SKU for the variant", example = "APPLE-RED-1KG", required = true)
    private String sku;

    @NotBlank(message = "Label is required")
    @Schema(description = "Display label for the variant", example = "1 kg", required = true)
    private String label;

    @Schema(description = "Optional attributes as key-value pairs. Examples: {\"weight\": \"1 kg\", \"packaging\": \"bag\"}, {\"size\": \"M\", \"color\": \"Black\"}", 
            example = "{\"weight\": \"1 kg\", \"packaging\": \"bag\"}")
    private Map<String, String> attributes;

    public VariantCreateRequestDto() {
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}

