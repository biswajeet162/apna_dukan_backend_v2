package com.apna_dukan_backend.catalog.pricing.repository;

import com.apna_dukan_backend.catalog.pricing.model.PricingEntity;
import com.apna_dukan_backend.catalog.product.model.ProductEntity;
import com.apna_dukan_backend.catalog.variant.model.VariantEntity;
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
    
    List<PricingEntity> findByVariantId(UUID variantId);
    
    @Query("SELECT p FROM PricingEntity p WHERE p.variantId IN :variantIds AND p.active = true")
    List<PricingEntity> findByVariantIdInAndActiveTrue(@Param("variantIds") List<UUID> variantIds);
    
    @Query("SELECT p FROM PricingEntity p WHERE p.variantId IN :variantIds")
    List<PricingEntity> findByVariantIdIn(@Param("variantIds") List<UUID> variantIds);
    
    @Query("SELECT p FROM PricingEntity p WHERE p.variantId IN " +
           "(SELECT v.variantId FROM VariantEntity v WHERE v.productId = :productId)")
    List<PricingEntity> findByProductId(@Param("productId") UUID productId);
    
    @Query("SELECT p FROM PricingEntity p WHERE p.variantId IN " +
           "(SELECT v.variantId FROM VariantEntity v WHERE v.productId IN " +
           "(SELECT pr.productId FROM ProductEntity pr WHERE pr.productGroupId = :productGroupId))")
    List<PricingEntity> findByProductGroupId(@Param("productGroupId") UUID productGroupId);
}

