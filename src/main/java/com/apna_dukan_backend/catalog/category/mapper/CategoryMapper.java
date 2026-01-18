package com.apna_dukan_backend.catalog.category.mapper;

import com.apna_dukan_backend.catalog.category.dto.CategoryDto;
import com.apna_dukan_backend.catalog.category.model.CategoryEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public CategoryDto toDto(CategoryEntity entity) {
        if (entity == null) {
            return null;
        }
        return new CategoryDto(
                entity.getCategoryId(),
                entity.getSectionId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCode(),
                entity.getDisplayOrder(),
                entity.isEnabled()
        );
    }

    public List<CategoryDto> toDtoList(List<CategoryEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}

