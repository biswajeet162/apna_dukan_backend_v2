package com.apna_dukan_backend.catalog.productmetrics.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ProductMetricsAdminDto {
    private UUID productId;
    private BigDecimal averageRating;
    private Integer ratingCount;
    private Integer totalPurchased;
    private LocalDateTime lastUpdatedAt;

    public ProductMetricsAdminDto() {
    }

    public ProductMetricsAdminDto(UUID productId, BigDecimal averageRating, Integer ratingCount,
                                  Integer totalPurchased, LocalDateTime lastUpdatedAt) {
        this.productId = productId;
        this.averageRating = averageRating;
        this.ratingCount = ratingCount;
        this.totalPurchased = totalPurchased;
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public BigDecimal getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(BigDecimal averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public Integer getTotalPurchased() {
        return totalPurchased;
    }

    public void setTotalPurchased(Integer totalPurchased) {
        this.totalPurchased = totalPurchased;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }
}

