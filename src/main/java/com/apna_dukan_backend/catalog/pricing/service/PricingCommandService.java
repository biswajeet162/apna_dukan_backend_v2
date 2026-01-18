package com.apna_dukan_backend.catalog.pricing.service;

import com.apna_dukan_backend.catalog.pricing.exception.DuplicateActivePricingException;
import com.apna_dukan_backend.catalog.pricing.exception.InvalidPricingException;
import com.apna_dukan_backend.catalog.pricing.exception.PricingNotFoundException;
import com.apna_dukan_backend.catalog.pricing.model.PricingEntity;
import com.apna_dukan_backend.catalog.pricing.model.dto.PricingAdminResponseDto;
import com.apna_dukan_backend.catalog.pricing.model.dto.PricingCreateRequestDto;
import com.apna_dukan_backend.catalog.pricing.model.dto.PricingUpdateRequestDto;
import com.apna_dukan_backend.catalog.pricing.repository.PricingRepository;
import com.apna_dukan_backend.catalog.pricing.service.mapper.PricingMapper;
import com.apna_dukan_backend.catalog.variant.exception.VariantNotFoundException;
import com.apna_dukan_backend.catalog.variant.model.VariantEntity;
import com.apna_dukan_backend.catalog.variant.repository.VariantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PricingCommandService {
    private final PricingRepository pricingRepository;
    private final VariantRepository variantRepository;
    private final PricingMapper pricingMapper;

    public PricingCommandService(PricingRepository pricingRepository,
                                VariantRepository variantRepository,
                                PricingMapper pricingMapper) {
        this.pricingRepository = pricingRepository;
        this.variantRepository = variantRepository;
        this.pricingMapper = pricingMapper;
    }

    public PricingAdminResponseDto createPricing(UUID variantId, PricingCreateRequestDto request) {
        // Validate variant exists
        VariantEntity variant = variantRepository.findById(variantId)
                .orElseThrow(() -> new VariantNotFoundException("Variant not found with id: " + variantId));

        // Validate variant is enabled
        if (!variant.isEnabled()) {
            throw new InvalidPricingException("Cannot create pricing for disabled variant: " + variantId);
        }

        // Validate pricing values
        validatePricingValues(request.getMrp(), request.getSellingPrice());

        // Validate date range if both dates are provided
        if (request.getValidFrom() != null && request.getValidTo() != null) {
            validateDateRange(request.getValidFrom(), request.getValidTo());
        }

        // Calculate discount percent
        Integer discountPercent = calculateDiscountPercent(request.getMrp(), request.getSellingPrice());

        // Check if there's an active pricing for this variant
        Optional<PricingEntity> existingActivePricing = pricingRepository.findByVariantIdAndActiveTrue(variantId);
        if (existingActivePricing.isPresent()) {
            // Deactivate old pricing
            PricingEntity oldPricing = existingActivePricing.get();
            oldPricing.setActive(false);
            oldPricing.setUpdatedAt(LocalDateTime.now());
            pricingRepository.save(oldPricing);
        }

        // Create new pricing entity
        PricingEntity entity = new PricingEntity();
        entity.setPricingId(UUID.randomUUID());
        entity.setVariantId(variantId);
        entity.setCurrency(request.getCurrency());
        entity.setMrp(request.getMrp());
        entity.setSellingPrice(request.getSellingPrice());
        entity.setDiscountPercent(discountPercent);
        entity.setValidFrom(request.getValidFrom());
        entity.setValidTo(request.getValidTo());
        entity.setActive(true); // Default to active
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());

        PricingEntity saved = pricingRepository.save(entity);
        return pricingMapper.toAdminDto(saved);
    }

    public PricingAdminResponseDto updatePricing(UUID pricingId, PricingUpdateRequestDto request) {
        // Validate pricing exists
        PricingEntity entity = pricingRepository.findById(pricingId)
                .orElseThrow(() -> new PricingNotFoundException("Pricing not found with id: " + pricingId));

        // Validate variant is still enabled
        VariantEntity variant = variantRepository.findById(entity.getVariantId())
                .orElseThrow(() -> new VariantNotFoundException("Variant not found with id: " + entity.getVariantId()));

        if (!variant.isEnabled()) {
            throw new InvalidPricingException("Cannot update pricing for disabled variant: " + entity.getVariantId());
        }

        // Update fields if provided
        boolean priceChanged = false;
        if (request.getMrp() != null) {
            entity.setMrp(request.getMrp());
            priceChanged = true;
        }
        if (request.getSellingPrice() != null) {
            entity.setSellingPrice(request.getSellingPrice());
            priceChanged = true;
        }

        // Validate pricing values if prices were updated
        if (priceChanged) {
            validatePricingValues(entity.getMrp(), entity.getSellingPrice());
            // Recalculate discount percent
            entity.setDiscountPercent(calculateDiscountPercent(entity.getMrp(), entity.getSellingPrice()));
        }

        // Update date range if provided
        if (request.getValidFrom() != null) {
            entity.setValidFrom(request.getValidFrom());
        }
        if (request.getValidTo() != null) {
            entity.setValidTo(request.getValidTo());
        }

        // Validate date range if both dates are present
        if (entity.getValidFrom() != null && entity.getValidTo() != null) {
            validateDateRange(entity.getValidFrom(), entity.getValidTo());
        }

        // Handle active status update
        if (request.getActive() != null) {
            if (request.getActive()) {
                // If activating, ensure no other active pricing exists for this variant
                Optional<PricingEntity> existingActivePricing = pricingRepository.findByVariantIdAndActiveTrue(entity.getVariantId());
                if (existingActivePricing.isPresent() && !existingActivePricing.get().getPricingId().equals(pricingId)) {
                    throw new DuplicateActivePricingException("Another active pricing already exists for variant: " + entity.getVariantId());
                }
            }
            entity.setActive(request.getActive());
        }

        entity.setUpdatedAt(LocalDateTime.now());

        PricingEntity saved = pricingRepository.save(entity);
        return pricingMapper.toAdminDto(saved);
    }

    private void validatePricingValues(BigDecimal mrp, BigDecimal sellingPrice) {
        if (mrp.compareTo(sellingPrice) < 0) {
            throw new InvalidPricingException("MRP must be greater than or equal to selling price. MRP: " + mrp + ", Selling Price: " + sellingPrice);
        }
        if (mrp.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidPricingException("MRP must be greater than 0");
        }
        if (sellingPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidPricingException("Selling price must be greater than 0");
        }
    }

    private void validateDateRange(LocalDateTime validFrom, LocalDateTime validTo) {
        if (validFrom.isAfter(validTo)) {
            throw new InvalidPricingException("Valid from date must be before or equal to valid to date");
        }
    }

    private Integer calculateDiscountPercent(BigDecimal mrp, BigDecimal sellingPrice) {
        if (mrp.compareTo(BigDecimal.ZERO) == 0) {
            return 0;
        }
        BigDecimal discount = mrp.subtract(sellingPrice);
        BigDecimal discountPercent = discount.divide(mrp, 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
        return discountPercent.setScale(0, RoundingMode.HALF_UP).intValue();
    }
}

