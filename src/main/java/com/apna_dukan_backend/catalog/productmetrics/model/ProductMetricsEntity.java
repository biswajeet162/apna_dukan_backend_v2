package com.apna_dukan_backend.catalog.productmetrics.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "product_metrics")
@EntityListeners(AuditingEntityListener.class)
public class ProductMetricsEntity {
    @Id
    @Column(name = "product_id", columnDefinition = "UUID")
    private UUID productId;

    @Column(name = "average_rating", precision = 2, scale = 1)
    private BigDecimal averageRating;

    @Column(name = "rating_count", nullable = false)
    private Integer ratingCount;

    @Column(name = "total_purchased", nullable = false)
    private Integer totalPurchased;

    @LastModifiedDate
    @Column(name = "last_updated_at", nullable = false)
    private LocalDateTime lastUpdatedAt;

    public ProductMetricsEntity() {
        this.ratingCount = 0;
        this.totalPurchased = 0;
    }

    public ProductMetricsEntity(UUID productId, BigDecimal averageRating, Integer ratingCount,
                               Integer totalPurchased, LocalDateTime lastUpdatedAt) {
        this.productId = productId;
        this.averageRating = averageRating;
        this.ratingCount = ratingCount != null ? ratingCount : 0;
        this.totalPurchased = totalPurchased != null ? totalPurchased : 0;
        this.lastUpdatedAt = lastUpdatedAt;
    }

    // Getters and Setters
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

