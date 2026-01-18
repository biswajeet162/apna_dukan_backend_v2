package com.apna_dukan_backend.catalog.product.model.dto;

import java.util.List;

public class ProductImageDto {
    private String primary;
    private List<String> gallery;

    public ProductImageDto() {
    }

    public ProductImageDto(String primary, List<String> gallery) {
        this.primary = primary;
        this.gallery = gallery;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public List<String> getGallery() {
        return gallery;
    }

    public void setGallery(List<String> gallery) {
        this.gallery = gallery;
    }
}

