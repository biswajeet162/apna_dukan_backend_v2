package com.apna_dukan_backend.catalog.productgroup.mapper;

import com.apna_dukan_backend.catalog.productgroup.dto.ProductGroupAdminDto;
import com.apna_dukan_backend.catalog.productgroup.dto.ProductGroupDto;
import com.apna_dukan_backend.catalog.productgroup.model.ProductGroupEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductGroupMapper {

    public ProductGroupDto toDto(ProductGroupEntity entity) {
        if (entity == null) {
            return null;
        }
        return new ProductGroupDto(
                entity.getProductGroupId(),
                entity.getSubCategoryId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCode(),
                entity.getDisplayOrder(),
                entity.isEnabled(),
                entity.getImageUrls()
        );
    }

    public List<ProductGroupDto> toDtoList(List<ProductGroupEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ProductGroupAdminDto toAdminDto(ProductGroupEntity entity) {
        if (entity == null) {
            return null;
        }
        return new ProductGroupAdminDto(
                entity.getProductGroupId(),
                entity.getSubCategoryId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCode(),
                entity.getDisplayOrder(),
                entity.isEnabled(),
                entity.getImageUrls(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public List<ProductGroupAdminDto> toAdminDtoList(List<ProductGroupEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toAdminDto)
                .collect(Collectors.toList());
    }
}


