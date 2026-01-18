package com.apna_dukan_backend.catalog.pricing.service;

import com.apna_dukan_backend.catalog.pricing.model.PricingEntity;
import com.apna_dukan_backend.catalog.pricing.repository.PricingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PricingQueryService {
    private final PricingRepository pricingRepository;

    public PricingQueryService(PricingRepository pricingRepository) {
        this.pricingRepository = pricingRepository;
    }

    /**
     * Get active pricing for a list of variant IDs
     * @param variantIds List of variant IDs
     * @return Map of variantId to PricingEntity (only active pricings)
     */
    public Map<UUID, PricingEntity> getActivePricingByVariantIds(List<UUID> variantIds) {
        if (variantIds == null || variantIds.isEmpty()) {
            return Collections.emptyMap();
        }
        List<PricingEntity> pricings = pricingRepository.findByVariantIdInAndActiveTrue(variantIds);
        return pricings.stream()
                .collect(Collectors.toMap(PricingEntity::getVariantId, p -> p));
    }

    /**
     * Get all pricing (active and inactive) for a list of variant IDs
     * @param variantIds List of variant IDs
     * @return Map of variantId to PricingEntity (all pricings)
     */
    public Map<UUID, PricingEntity> getAllPricingByVariantIds(List<UUID> variantIds) {
        if (variantIds == null || variantIds.isEmpty()) {
            return Collections.emptyMap();
        }
        List<PricingEntity> pricings = pricingRepository.findByVariantIdIn(variantIds);
        return pricings.stream()
                .collect(Collectors.toMap(PricingEntity::getVariantId, p -> p));
    }

    /**
     * Get active pricing for a single variant
     * @param variantId Variant ID
     * @return Optional PricingEntity
     */
    public java.util.Optional<PricingEntity> getActivePricingByVariantId(UUID variantId) {
        return pricingRepository.findByVariantIdAndActiveTrue(variantId);
    }
}

