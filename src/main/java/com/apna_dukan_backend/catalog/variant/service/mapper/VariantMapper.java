package com.apna_dukan_backend.catalog.variant.service.mapper;

import com.apna_dukan_backend.catalog.variant.model.VariantEntity;
import com.apna_dukan_backend.catalog.variant.model.domain.Variant;
import com.apna_dukan_backend.catalog.variant.model.dto.VariantAdminResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VariantMapper {

    public Variant toDomain(VariantEntity entity) {
        if (entity == null) {
            return null;
        }
        Variant variant = new Variant();
        variant.setVariantId(entity.getVariantId());
        variant.setProductId(entity.getProductId());
        variant.setSku(entity.getSku());
        variant.setLabel(entity.getLabel());
        variant.setAttributes(entity.getAttributes());
        variant.setEnabled(entity.isEnabled());
        variant.setCreatedAt(entity.getCreatedAt());
        variant.setUpdatedAt(entity.getUpdatedAt());
        return variant;
    }

    public VariantEntity toEntity(Variant variant) {
        if (variant == null) {
            return null;
        }
        VariantEntity entity = new VariantEntity();
        entity.setVariantId(variant.getVariantId());
        entity.setProductId(variant.getProductId());
        entity.setSku(variant.getSku());
        entity.setLabel(variant.getLabel());
        entity.setAttributes(variant.getAttributes());
        entity.setEnabled(variant.isEnabled());
        entity.setCreatedAt(variant.getCreatedAt());
        entity.setUpdatedAt(variant.getUpdatedAt());
        return entity;
    }

    public VariantAdminResponseDto toAdminDto(VariantEntity entity) {
        if (entity == null) {
            return null;
        }
        return new VariantAdminResponseDto(
                entity.getVariantId(),
                entity.getProductId(),
                entity.getSku(),
                entity.getLabel(),
                entity.getAttributes(),
                entity.isEnabled(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public List<VariantAdminResponseDto> toAdminDtoList(List<VariantEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toAdminDto)
                .collect(Collectors.toList());
    }
}





