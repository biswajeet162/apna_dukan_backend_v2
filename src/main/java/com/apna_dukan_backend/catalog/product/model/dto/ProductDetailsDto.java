package com.apna_dukan_backend.catalog.product.model.dto;

import java.util.List;
import java.util.UUID;

public class ProductDetailsDto {
    private UUID productId;
    private String name;
    private String brand;
    private String description;
    private ProductImageDto images;
    private List<VariantDetailsDto> variants;

    public ProductDetailsDto() {
    }

    public ProductDetailsDto(UUID productId, String name, String brand, String description,
                            ProductImageDto images, List<VariantDetailsDto> variants) {
        this.productId = productId;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.images = images;
        this.variants = variants;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductImageDto getImages() {
        return images;
    }

    public void setImages(ProductImageDto images) {
        this.images = images;
    }

    public List<VariantDetailsDto> getVariants() {
        return variants;
    }

    public void setVariants(List<VariantDetailsDto> variants) {
        this.variants = variants;
    }
}




