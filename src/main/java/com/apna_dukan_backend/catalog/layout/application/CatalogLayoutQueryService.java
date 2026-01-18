package com.apna_dukan_backend.catalog.layout.application;

import com.apna_dukan_backend.catalog.layout.infrastructure.CatalogSectionEntity;
import com.apna_dukan_backend.catalog.layout.infrastructure.CatalogSectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogLayoutQueryService {
    private final CatalogSectionRepository catalogSectionRepository;

    public CatalogLayoutQueryService(CatalogSectionRepository catalogSectionRepository) {
        this.catalogSectionRepository = catalogSectionRepository;
    }

    public List<CatalogSectionDto> getAllSectionsForAdmin() {
        List<CatalogSectionEntity> entities = catalogSectionRepository.findAllByOrderByDisplayOrderAsc();
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<CatalogSectionDto> getEnabledSectionsForUser() {
        List<CatalogSectionEntity> entities = catalogSectionRepository.findByEnabledTrueOrderByDisplayOrderAsc();
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private CatalogSectionDto toDto(CatalogSectionEntity entity) {
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
}

