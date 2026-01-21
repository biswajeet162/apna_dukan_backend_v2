package com.apna_dukan_backend.catalog.productmetrics.model.dto;

import java.math.BigDecimal;

public class ProductMetricsViewDto {
    private RatingDto rating;
    private PopularityDto popularity;

    public ProductMetricsViewDto() {
    }

    public ProductMetricsViewDto(RatingDto rating, PopularityDto popularity) {
        this.rating = rating;
        this.popularity = popularity;
    }

    public RatingDto getRating() {
        return rating;
    }

    public void setRating(RatingDto rating) {
        this.rating = rating;
    }

    public PopularityDto getPopularity() {
        return popularity;
    }

    public void setPopularity(PopularityDto popularity) {
        this.popularity = popularity;
    }

    public static class RatingDto {
        private BigDecimal average;
        private Integer count;

        public RatingDto() {
        }

        public RatingDto(BigDecimal average, Integer count) {
            this.average = average;
            this.count = count;
        }

        public BigDecimal getAverage() {
            return average;
        }

        public void setAverage(BigDecimal average) {
            this.average = average;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }

    public static class PopularityDto {
        private Integer totalPurchased;

        public PopularityDto() {
        }

        public PopularityDto(Integer totalPurchased) {
            this.totalPurchased = totalPurchased;
        }

        public Integer getTotalPurchased() {
            return totalPurchased;
        }

        public void setTotalPurchased(Integer totalPurchased) {
            this.totalPurchased = totalPurchased;
        }
    }
}

