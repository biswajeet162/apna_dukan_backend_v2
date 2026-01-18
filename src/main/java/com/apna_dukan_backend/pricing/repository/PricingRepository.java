package com.apna_dukan_backend.pricing.repository;

import com.apna_dukan_backend.pricing.model.PricingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PricingRepository extends JpaRepository<PricingEntity, UUID> {
    Optional<PricingEntity> findByVariantIdAndActiveTrue(UUID variantId);
    
    @Query("SELECT p FROM PricingEntity p WHERE p.variantId IN :variantIds AND p.active = true")
    List<PricingEntity> findByVariantIdInAndActiveTrue(@Param("variantIds") List<UUID> variantIds);
    
    @Query("SELECT p FROM PricingEntity p WHERE p.variantId IN :variantIds")
    List<PricingEntity> findByVariantIdIn(@Param("variantIds") List<UUID> variantIds);
}

