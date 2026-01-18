package com.apna_dukan_backend.catalog.product.model.dto;

import java.util.Map;
import java.util.UUID;

public class VariantDetailsDto {
    private UUID variantId;
    private String label;
    private Map<String, String> attributes;
    private PricingDetailsDto pricing;
    private AvailabilityDto availability;

    public VariantDetailsDto() {
    }

    public VariantDetailsDto(UUID variantId, String label, Map<String, String> attributes,
                            PricingDetailsDto pricing, AvailabilityDto availability) {
        this.variantId = variantId;
        this.label = label;
        this.attributes = attributes;
        this.pricing = pricing;
        this.availability = availability;
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

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public PricingDetailsDto getPricing() {
        return pricing;
    }

    public void setPricing(PricingDetailsDto pricing) {
        this.pricing = pricing;
    }

    public AvailabilityDto getAvailability() {
        return availability;
    }

    public void setAvailability(AvailabilityDto availability) {
        this.availability = availability;
    }
}

