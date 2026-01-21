package com.apna_dukan_backend.catalog.productmetrics.service;

import com.apna_dukan_backend.catalog.product.exception.ProductNotFoundException;
import com.apna_dukan_backend.catalog.product.repository.ProductRepository;
import com.apna_dukan_backend.catalog.productmetrics.model.ProductMetricsEntity;
import com.apna_dukan_backend.catalog.productmetrics.model.domain.ProductMetrics;
import com.apna_dukan_backend.catalog.productmetrics.model.dto.ProductMetricsUpdateRequestDto;
import com.apna_dukan_backend.catalog.productmetrics.repository.ProductMetricsRepository;
import com.apna_dukan_backend.catalog.productmetrics.service.mapper.ProductMetricsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class ProductMetricsCommandService {
    private final ProductMetricsRepository metricsRepository;
    private final ProductRepository productRepository;
    private final ProductMetricsMapper mapper;

    public ProductMetricsCommandService(
            ProductMetricsRepository metricsRepository,
            ProductRepository productRepository,
            ProductMetricsMapper mapper) {
        this.metricsRepository = metricsRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    /**
     * Initialize metrics for a product if they don't exist
     */
    public void initializeMetricsIfAbsent(UUID productId) {
        // Validate product exists
        productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        // Check if metrics already exist
        if (metricsRepository.findByProductId(productId).isPresent()) {
            return; // Already initialized
        }

        // Create new metrics entity
        ProductMetricsEntity entity = new ProductMetricsEntity();
        entity.setProductId(productId);
        entity.setAverageRating(null);
        entity.setRatingCount(0);
        entity.setTotalPurchased(0);
        entity.setLastUpdatedAt(LocalDateTime.now());

        metricsRepository.save(entity);
    }

    /**
     * Increment purchase count for a product
     */
    public void incrementPurchase(UUID productId) {
        // Validate product exists
        productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        // Get or create metrics
        ProductMetricsEntity entity = metricsRepository.findByProductId(productId)
                .orElseGet(() -> {
                    ProductMetricsEntity newEntity = new ProductMetricsEntity();
                    newEntity.setProductId(productId);
                    newEntity.setAverageRating(null);
                    newEntity.setRatingCount(0);
                    newEntity.setTotalPurchased(0);
                    return newEntity;
                });

        // Increment purchase count
        entity.setTotalPurchased(entity.getTotalPurchased() + 1);
        entity.setLastUpdatedAt(LocalDateTime.now());

        metricsRepository.save(entity);
    }

    /**
     * Add a rating and recalculate average rating
     */
    public void addRating(UUID productId, Integer rating) {
        // Validate rating
        if (rating == null || rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        // Validate product exists
        productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        // Get or create metrics
        ProductMetricsEntity entity = metricsRepository.findByProductId(productId)
                .orElseGet(() -> {
                    ProductMetricsEntity newEntity = new ProductMetricsEntity();
                    newEntity.setProductId(productId);
                    newEntity.setAverageRating(null);
                    newEntity.setRatingCount(0);
                    newEntity.setTotalPurchased(0);
                    return newEntity;
                });

        // Calculate new average rating
        Integer oldCount = entity.getRatingCount();
        BigDecimal oldAverage = entity.getAverageRating();

        if (oldCount == 0 || oldAverage == null) {
            // First rating
            entity.setAverageRating(BigDecimal.valueOf(rating));
        } else {
            // Recalculate: newAvg = ((oldAvg * oldCount) + rating) / (oldCount + 1)
            BigDecimal totalSum = oldAverage.multiply(BigDecimal.valueOf(oldCount))
                    .add(BigDecimal.valueOf(rating));
            BigDecimal newAverage = totalSum.divide(
                    BigDecimal.valueOf(oldCount + 1),
                    1,
                    RoundingMode.HALF_UP
            );
            entity.setAverageRating(newAverage);
        }

        // Increment rating count
        entity.setRatingCount(oldCount + 1);
        entity.setLastUpdatedAt(LocalDateTime.now());

        metricsRepository.save(entity);
    }

    /**
     * Admin update metrics (manual override)
     */
    public ProductMetrics adminUpdateMetrics(UUID productId, ProductMetricsUpdateRequestDto request) {
        // Validate product exists
        productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        // Get or create metrics
        ProductMetricsEntity entity = metricsRepository.findByProductId(productId)
                .orElseGet(() -> {
                    ProductMetricsEntity newEntity = new ProductMetricsEntity();
                    newEntity.setProductId(productId);
                    return newEntity;
                });

        // Update metrics
        entity.setAverageRating(request.getAverageRating());
        entity.setRatingCount(request.getRatingCount());
        entity.setTotalPurchased(request.getTotalPurchased());
        entity.setLastUpdatedAt(LocalDateTime.now());

        ProductMetricsEntity saved = metricsRepository.save(entity);
        return mapper.toDomain(saved);
    }
}

