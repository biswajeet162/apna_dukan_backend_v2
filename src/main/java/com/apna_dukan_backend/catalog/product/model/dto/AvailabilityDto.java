package com.apna_dukan_backend.catalog.product.model.dto;

public class AvailabilityDto {
    private boolean inStock;
    private int availableQuantity;

    public AvailabilityDto() {
    }

    public AvailabilityDto(boolean inStock, int availableQuantity) {
        this.inStock = inStock;
        this.availableQuantity = availableQuantity;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
}

