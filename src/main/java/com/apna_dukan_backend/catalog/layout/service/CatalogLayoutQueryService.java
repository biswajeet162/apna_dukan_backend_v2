package com.apna_dukan_backend.catalog.layout.service;

import com.apna_dukan_backend.catalog.layout.dto.CatalogSectionDto;
import com.apna_dukan_backend.catalog.layout.exception.SectionNotFoundException;
import com.apna_dukan_backend.catalog.layout.mapper.CatalogSectionMapper;
import com.apna_dukan_backend.catalog.layout.model.CatalogSectionEntity;
import com.apna_dukan_backend.catalog.layout.repository.CatalogSectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CatalogLayoutQueryService {
    private final CatalogSectionRepository catalogSectionRepository;
    private final CatalogSectionMapper catalogSectionMapper;

    public CatalogLayoutQueryService(CatalogSectionRepository catalogSectionRepository,
                                     CatalogSectionMapper catalogSectionMapper) {
        this.catalogSectionRepository = catalogSectionRepository;
        this.catalogSectionMapper = catalogSectionMapper;
    }

    public List<CatalogSectionDto> getAllSectionsForAdmin() {
        List<CatalogSectionEntity> entities = catalogSectionRepository.findAllByOrderByDisplayOrderAsc();
        return catalogSectionMapper.toDtoList(entities);
    }

    public List<CatalogSectionDto> getEnabledSectionsForUser() {
        List<CatalogSectionEntity> entities = catalogSectionRepository.findByEnabledTrueOrderByDisplayOrderAsc();
        return catalogSectionMapper.toDtoList(entities);
    }

    public CatalogSectionDto getSectionById(UUID sectionId) {
        CatalogSectionEntity entity = catalogSectionRepository.findById(sectionId)
                .orElseThrow(() -> new SectionNotFoundException("Section not found with id: " + sectionId));
        return catalogSectionMapper.toDto(entity);
    }
}

