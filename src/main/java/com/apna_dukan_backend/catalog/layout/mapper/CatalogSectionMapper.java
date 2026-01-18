package com.apna_dukan_backend.catalog.layout.mapper;

import com.apna_dukan_backend.catalog.layout.dto.CatalogSectionDto;
import com.apna_dukan_backend.catalog.layout.model.CatalogSectionEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CatalogSectionMapper {

    public CatalogSectionDto toDto(CatalogSectionEntity entity) {
        if (entity == null) {
            return null;
        }
        return new CatalogSectionDto(
                entity.getSectionId(),
                entity.getSectionCode(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getLayoutType(),
                entity.getScrollType(),
                entity.getDisplayOrder(),
                entity.isEnabled(),
                entity.isPersonalized(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public List<CatalogSectionDto> toDtoList(List<CatalogSectionEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public CatalogSectionEntity toEntity(CatalogSectionDto dto) {
        if (dto == null) {
            return null;
        }
        CatalogSectionEntity entity = new CatalogSectionEntity();
        entity.setSectionId(dto.getSectionId());
        entity.setSectionCode(dto.getSectionCode());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setLayoutType(dto.getLayoutType());
        entity.setScrollType(dto.getScrollType());
        entity.setDisplayOrder(dto.getDisplayOrder());
        entity.setEnabled(dto.isEnabled());
        entity.setPersonalized(dto.isPersonalized());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        return entity;
    }
}




