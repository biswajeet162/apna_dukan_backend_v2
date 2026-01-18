package com.apna_dukan_backend.catalog.pricing.service;

import com.apna_dukan_backend.catalog.pricing.model.PricingEntity;
import com.apna_dukan_backend.catalog.pricing.model.dto.PricingAdminResponseDto;
import com.apna_dukan_backend.catalog.pricing.repository.PricingRepository;
import com.apna_dukan_backend.catalog.pricing.service.mapper.PricingMapper;
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
    private final PricingMapper pricingMapper;

    public PricingQueryService(PricingRepository pricingRepository, PricingMapper pricingMapper) {
        this.pricingRepository = pricingRepository;
        this.pricingMapper = pricingMapper;
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

    /**
     * Get all pricing records (active and inactive)
     * @return List of all pricing records
     */
    public List<PricingAdminResponseDto> getAllPricing() {
        List<PricingEntity> allPricing = pricingRepository.findAll();
        return pricingMapper.toAdminDtoList(allPricing);
    }

    /**
     * Get all pricing records for a variant (active and inactive)
     * @param variantId Variant ID
     * @return List of pricing records for the variant
     */
    public List<PricingAdminResponseDto> getPricingByVariantId(UUID variantId) {
        List<PricingEntity> pricingList = pricingRepository.findByVariantId(variantId);
        return pricingMapper.toAdminDtoList(pricingList);
    }

    /**
     * Get all pricing records for a product (active and inactive)
     * @param productId Product ID
     * @return List of pricing records for all variants of the product
     */
    public List<PricingAdminResponseDto> getPricingByProductId(UUID productId) {
        List<PricingEntity> pricingList = pricingRepository.findByProductId(productId);
        return pricingMapper.toAdminDtoList(pricingList);
    }

    /**
     * Get all pricing records for a product group (active and inactive)
     * @param productGroupId Product Group ID
     * @return List of pricing records for all variants of all products in the product group
     */
    public List<PricingAdminResponseDto> getPricingByProductGroupId(UUID productGroupId) {
        List<PricingEntity> pricingList = pricingRepository.findByProductGroupId(productGroupId);
        return pricingMapper.toAdminDtoList(pricingList);
    }
}

