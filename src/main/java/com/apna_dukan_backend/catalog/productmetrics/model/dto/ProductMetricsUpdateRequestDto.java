package com.apna_dukan_backend.catalog.productmetrics.model.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductMetricsUpdateRequestDto {
    @NotNull(message = "Average rating is required")
    @DecimalMin(value = "0.0", message = "Average rating must be at least 0.0")
    @DecimalMax(value = "5.0", message = "Average rating must be at most 5.0")
    private BigDecimal averageRating;

    @NotNull(message = "Rating count is required")
    @Min(value = 0, message = "Rating count must be non-negative")
    private Integer ratingCount;

    @NotNull(message = "Total purchased is required")
    @Min(value = 0, message = "Total purchased must be non-negative")
    private Integer totalPurchased;

    public ProductMetricsUpdateRequestDto() {
    }

    public ProductMetricsUpdateRequestDto(BigDecimal averageRating, Integer ratingCount, Integer totalPurchased) {
        this.averageRating = averageRating;
        this.ratingCount = ratingCount;
        this.totalPurchased = totalPurchased;
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
}

