package com.apna_dukan_backend.catalog.productmetrics.repository;

import com.apna_dukan_backend.catalog.productmetrics.model.ProductMetricsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductMetricsRepository extends JpaRepository<ProductMetricsEntity, UUID> {
    Optional<ProductMetricsEntity> findByProductId(UUID productId);

    @Query("SELECT pm FROM ProductMetricsEntity pm WHERE pm.productId IN :productIds")
    List<ProductMetricsEntity> findByProductIdIn(@Param("productIds") List<UUID> productIds);
}

