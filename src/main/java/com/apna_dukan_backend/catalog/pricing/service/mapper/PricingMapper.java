package com.apna_dukan_backend.catalog.pricing.service.mapper;

import com.apna_dukan_backend.catalog.pricing.model.PricingEntity;
import com.apna_dukan_backend.catalog.pricing.model.domain.Pricing;
import com.apna_dukan_backend.catalog.pricing.model.dto.PricingAdminResponseDto;
import org.springframework.stereotype.Component;

@Component
public class PricingMapper {

    public Pricing toDomain(PricingEntity entity) {
        if (entity == null) {
            return null;
        }
        Pricing pricing = new Pricing();
        pricing.setPricingId(entity.getPricingId());
        pricing.setVariantId(entity.getVariantId());
        pricing.setCurrency(entity.getCurrency());
        pricing.setMrp(entity.getMrp());
        pricing.setSellingPrice(entity.getSellingPrice());
        pricing.setDiscountPercent(entity.getDiscountPercent());
        pricing.setValidFrom(entity.getValidFrom());
        pricing.setValidTo(entity.getValidTo());
        pricing.setActive(entity.isActive());
        pricing.setCreatedAt(entity.getCreatedAt());
        pricing.setUpdatedAt(entity.getUpdatedAt());
        return pricing;
    }

    public PricingEntity toEntity(Pricing pricing) {
        if (pricing == null) {
            return null;
        }
        PricingEntity entity = new PricingEntity();
        entity.setPricingId(pricing.getPricingId());
        entity.setVariantId(pricing.getVariantId());
        entity.setCurrency(pricing.getCurrency());
        entity.setMrp(pricing.getMrp());
        entity.setSellingPrice(pricing.getSellingPrice());
        entity.setDiscountPercent(pricing.getDiscountPercent());
        entity.setValidFrom(pricing.getValidFrom());
        entity.setValidTo(pricing.getValidTo());
        entity.setActive(pricing.isActive());
        entity.setCreatedAt(pricing.getCreatedAt());
        entity.setUpdatedAt(pricing.getUpdatedAt());
        return entity;
    }

    public PricingAdminResponseDto toAdminDto(PricingEntity entity) {
        if (entity == null) {
            return null;
        }
        return new PricingAdminResponseDto(
                entity.getPricingId(),
                entity.getVariantId(),
                entity.getCurrency(),
                entity.getMrp(),
                entity.getSellingPrice(),
                entity.getDiscountPercent(),
                entity.getValidFrom(),
                entity.getValidTo(),
                entity.isActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public java.util.List<PricingAdminResponseDto> toAdminDtoList(java.util.List<PricingEntity> entities) {
        if (entities == null) {
            return java.util.Collections.emptyList();
        }
        return entities.stream()
                .map(this::toAdminDto)
                .collect(java.util.stream.Collectors.toList());
    }
}

