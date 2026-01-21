package com.apna_dukan_backend.catalog.product.model.dto;

import com.apna_dukan_backend.catalog.productmetrics.model.dto.ProductMetricsViewDto;

import java.util.UUID;

public class ProductListItemDto {
    private UUID productId;
    private String name;
    private String brand;
    private ProductImageDto image;
    private VariantSummaryDto defaultVariant;
    private PricingSummaryDto pricing;
    private AvailabilityDto availability;
    private ProductMetricsViewDto metrics;

    public ProductListItemDto() {
    }

    public ProductListItemDto(UUID productId, String name, String brand, ProductImageDto image,
                              VariantSummaryDto defaultVariant, PricingSummaryDto pricing,
                              AvailabilityDto availability, ProductMetricsViewDto metrics) {
        this.productId = productId;
        this.name = name;
        this.brand = brand;
        this.image = image;
        this.defaultVariant = defaultVariant;
        this.pricing = pricing;
        this.availability = availability;
        this.metrics = metrics;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public ProductImageDto getImage() {
        return image;
    }

    public void setImage(ProductImageDto image) {
        this.image = image;
    }

    public VariantSummaryDto getDefaultVariant() {
        return defaultVariant;
    }

    public void setDefaultVariant(VariantSummaryDto defaultVariant) {
        this.defaultVariant = defaultVariant;
    }

    public PricingSummaryDto getPricing() {
        return pricing;
    }

    public void setPricing(PricingSummaryDto pricing) {
        this.pricing = pricing;
    }

    public AvailabilityDto getAvailability() {
        return availability;
    }

    public void setAvailability(AvailabilityDto availability) {
        this.availability = availability;
    }

    public ProductMetricsViewDto getMetrics() {
        return metrics;
    }

    public void setMetrics(ProductMetricsViewDto metrics) {
        this.metrics = metrics;
    }
}

