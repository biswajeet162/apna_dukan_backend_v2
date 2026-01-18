package com.apna_dukan_backend.catalog.variant.repository;

import com.apna_dukan_backend.catalog.variant.model.VariantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VariantRepository extends JpaRepository<VariantEntity, UUID> {
    List<VariantEntity> findByProductIdAndEnabledTrueOrderByIsDefaultDescDisplayOrderAsc(UUID productId);
    
    @Query("SELECT v FROM VariantEntity v WHERE v.productId = :productId AND v.enabled = true " +
           "ORDER BY v.isDefault DESC, v.displayOrder ASC")
    Optional<VariantEntity> findDefaultVariantByProductId(@Param("productId") UUID productId);
    
    List<VariantEntity> findByProductIdInAndEnabledTrue(List<UUID> productIds);
}

