package com.apna_dukan_backend.catalog.product.model.dto;

public class AvailabilityDto {
    private boolean inStock;

    public AvailabilityDto() {
    }

    public AvailabilityDto(boolean inStock) {
        this.inStock = inStock;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}

