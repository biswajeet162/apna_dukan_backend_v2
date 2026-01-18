package com.apna_dukan_backend.catalog.product.model.dto;

public class ProductImageDto {
    private String primary;

    public ProductImageDto() {
    }

    public ProductImageDto(String primary) {
        this.primary = primary;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }
}

