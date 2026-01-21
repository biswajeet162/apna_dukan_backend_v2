package com.apna_dukan_backend.catalog.productmetrics.service;

import com.apna_dukan_backend.catalog.productmetrics.model.ProductMetricsEntity;
import com.apna_dukan_backend.catalog.productmetrics.model.domain.ProductMetrics;
import com.apna_dukan_backend.catalog.productmetrics.model.dto.ProductMetricsAdminDto;
import com.apna_dukan_backend.catalog.productmetrics.model.dto.ProductMetricsViewDto;
import com.apna_dukan_backend.catalog.productmetrics.repository.ProductMetricsRepository;
import com.apna_dukan_backend.catalog.productmetrics.service.mapper.ProductMetricsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ProductMetricsQueryService {
    private final ProductMetricsRepository metricsRepository;
    private final ProductMetricsMapper mapper;

    public ProductMetricsQueryService(
            ProductMetricsRepository metricsRepository,
            ProductMetricsMapper mapper) {
        this.metricsRepository = metricsRepository;
        this.mapper = mapper;
    }

    /**
     * Get metrics for a single product (admin view)
     */
    public ProductMetricsAdminDto getMetricsByProductId(UUID productId) {
        ProductMetricsEntity entity = metricsRepository.findByProductId(productId)
                .orElse(null);

        if (entity == null) {
            return null;
        }

        ProductMetrics domain = mapper.toDomain(entity);
        return mapper.toAdminDto(domain);
    }

    /**
     * Get metrics for a single product (user view)
     */
    public ProductMetricsViewDto getMetricsViewByProductId(UUID productId) {
        ProductMetricsEntity entity = metricsRepository.findByProductId(productId)
                .orElse(null);

        if (entity == null) {
            return null;
        }

        ProductMetrics domain = mapper.toDomain(entity);
        return mapper.toViewDto(domain);
    }

    /**
     * Batch fetch metrics for multiple products
     * Returns a map of productId -> ProductMetricsViewDto
     */
    public Map<UUID, ProductMetricsViewDto> getMetricsViewByProductIds(List<UUID> productIds) {
        if (productIds == null || productIds.isEmpty()) {
            return Collections.emptyMap();
        }

        List<ProductMetricsEntity> entities = metricsRepository.findByProductIdIn(productIds);

        Map<UUID, ProductMetricsViewDto> metricsMap = new HashMap<>();
        for (ProductMetricsEntity entity : entities) {
            ProductMetrics domain = mapper.toDomain(entity);
            ProductMetricsViewDto viewDto = mapper.toViewDto(domain);
            metricsMap.put(entity.getProductId(), viewDto);
        }

        return metricsMap;
    }
}

