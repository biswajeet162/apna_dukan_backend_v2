package com.apna_dukan_backend.catalog.subcategory.mapper;

import com.apna_dukan_backend.catalog.subcategory.dto.SubCategoryAdminDto;
import com.apna_dukan_backend.catalog.subcategory.dto.SubCategoryDto;
import com.apna_dukan_backend.catalog.subcategory.model.SubCategoryEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SubCategoryMapper {

    public SubCategoryDto toDto(SubCategoryEntity entity) {
        if (entity == null) {
            return null;
        }
        return new SubCategoryDto(
                entity.getSubCategoryId(),
                entity.getCategoryId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCode(),
                entity.getDisplayOrder(),
                entity.isEnabled(),
                entity.getImageUrls()
        );
    }

    public List<SubCategoryDto> toDtoList(List<SubCategoryEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public SubCategoryAdminDto toAdminDto(SubCategoryEntity entity) {
        if (entity == null) {
            return null;
        }
        return new SubCategoryAdminDto(
                entity.getSubCategoryId(),
                entity.getCategoryId(),
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

    public List<SubCategoryAdminDto> toAdminDtoList(List<SubCategoryEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toAdminDto)
                .collect(Collectors.toList());
    }
}

