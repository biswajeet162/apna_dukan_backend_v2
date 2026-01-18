package com.apna_dukan_backend.catalog.variant.repository;

import com.apna_dukan_backend.catalog.variant.model.VariantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VariantRepository extends JpaRepository<VariantEntity, UUID> {
    Optional<VariantEntity> findBySku(String sku);
    
    List<VariantEntity> findByProductId(UUID productId);
    
    List<VariantEntity> findByProductIdAndEnabledTrue(UUID productId);
    
    List<VariantEntity> findByProductIdInAndEnabledTrue(List<UUID> productIds);
    
    List<VariantEntity> findByProductIdIn(List<UUID> productIds);
}

