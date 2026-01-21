package com.apna_dukan_backend.catalog.productmetrics.service.mapper;

import com.apna_dukan_backend.catalog.productmetrics.model.ProductMetricsEntity;
import com.apna_dukan_backend.catalog.productmetrics.model.domain.ProductMetrics;
import com.apna_dukan_backend.catalog.productmetrics.model.dto.ProductMetricsAdminDto;
import com.apna_dukan_backend.catalog.productmetrics.model.dto.ProductMetricsViewDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMetricsMapper {

    public ProductMetrics toDomain(ProductMetricsEntity entity) {
        if (entity == null) {
            return null;
        }
        return new ProductMetrics(
                entity.getProductId(),
                entity.getAverageRating(),
                entity.getRatingCount(),
                entity.getTotalPurchased(),
                entity.getLastUpdatedAt()
        );
    }

    public ProductMetricsEntity toEntity(ProductMetrics domain) {
        if (domain == null) {
            return null;
        }
        return new ProductMetricsEntity(
                domain.getProductId(),
                domain.getAverageRating(),
                domain.getRatingCount(),
                domain.getTotalPurchased(),
                domain.getLastUpdatedAt()
        );
    }

    public ProductMetricsAdminDto toAdminDto(ProductMetrics domain) {
        if (domain == null) {
            return null;
        }
        return new ProductMetricsAdminDto(
                domain.getProductId(),
                domain.getAverageRating(),
                domain.getRatingCount(),
                domain.getTotalPurchased(),
                domain.getLastUpdatedAt()
        );
    }

    public ProductMetricsViewDto toViewDto(ProductMetrics domain) {
        if (domain == null) {
            return null;
        }

        ProductMetricsViewDto.RatingDto rating = null;
        if (domain.getAverageRating() != null && domain.getRatingCount() != null && domain.getRatingCount() > 0) {
            rating = new ProductMetricsViewDto.RatingDto(
                    domain.getAverageRating(),
                    domain.getRatingCount()
            );
        }

        ProductMetricsViewDto.PopularityDto popularity = null;
        if (domain.getTotalPurchased() != null && domain.getTotalPurchased() > 0) {
            popularity = new ProductMetricsViewDto.PopularityDto(
                    domain.getTotalPurchased()
            );
        }

        return new ProductMetricsViewDto(rating, popularity);
    }
}

