package com.apna_dukan_backend.catalog.layout.service;

import com.apna_dukan_backend.catalog.layout.dto.BulkUpdateRequest;
import com.apna_dukan_backend.catalog.layout.dto.CatalogSectionDto;
import com.apna_dukan_backend.catalog.layout.dto.CreateSectionRequest;
import com.apna_dukan_backend.catalog.layout.dto.UpdateSectionRequest;
import com.apna_dukan_backend.catalog.layout.exception.SectionNotFoundException;
import com.apna_dukan_backend.catalog.layout.mapper.CatalogSectionMapper;
import com.apna_dukan_backend.catalog.layout.model.CatalogSectionEntity;
import com.apna_dukan_backend.catalog.layout.repository.CatalogSectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CatalogLayoutCommandService {
    private final CatalogSectionRepository catalogSectionRepository;
    private final CatalogSectionMapper catalogSectionMapper;

    public CatalogLayoutCommandService(CatalogSectionRepository catalogSectionRepository,
                                      CatalogSectionMapper catalogSectionMapper) {
        this.catalogSectionRepository = catalogSectionRepository;
        this.catalogSectionMapper = catalogSectionMapper;
    }

    public CatalogSectionDto createSection(CreateSectionRequest request) {
        CatalogSectionEntity entity = new CatalogSectionEntity();
        entity.setSectionId(UUID.randomUUID());
        entity.setSectionCode(request.getSectionCode());
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setLayoutType(request.getLayoutType());
        entity.setScrollType(request.getScrollType());
        entity.setDisplayOrder(request.getDisplayOrder());
        entity.setEnabled(request.getEnabled() != null ? request.getEnabled() : true);
        entity.setPersonalized(request.getPersonalized() != null ? request.getPersonalized() : false);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());

        CatalogSectionEntity saved = catalogSectionRepository.save(entity);
        return catalogSectionMapper.toDto(saved);
    }

    public CatalogSectionDto updateSection(UUID sectionId, UpdateSectionRequest request) {
        CatalogSectionEntity entity = catalogSectionRepository.findById(sectionId)
                .orElseThrow(() -> new SectionNotFoundException("Section not found with id: " + sectionId));

        if (request.getSectionCode() != null) {
            entity.setSectionCode(request.getSectionCode());
        }
        if (request.getTitle() != null) {
            entity.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            entity.setDescription(request.getDescription());
        }
        if (request.getLayoutType() != null) {
            entity.setLayoutType(request.getLayoutType());
        }
        if (request.getScrollType() != null) {
            entity.setScrollType(request.getScrollType());
        }
        if (request.getDisplayOrder() != null) {
            entity.setDisplayOrder(request.getDisplayOrder());
        }
        if (request.getEnabled() != null) {
            entity.setEnabled(request.getEnabled());
        }
        if (request.getPersonalized() != null) {
            entity.setPersonalized(request.getPersonalized());
        }
        entity.setUpdatedAt(LocalDateTime.now());

        CatalogSectionEntity updated = catalogSectionRepository.save(entity);
        return catalogSectionMapper.toDto(updated);
    }

    public void deleteSection(UUID sectionId) {
        if (!catalogSectionRepository.existsById(sectionId)) {
            throw new SectionNotFoundException("Section not found with id: " + sectionId);
        }
        catalogSectionRepository.deleteById(sectionId);
    }

    public List<CatalogSectionDto> bulkUpdate(BulkUpdateRequest request) {
        List<CatalogSectionEntity> entities = catalogSectionRepository.findAllById(request.getSectionIds());
        
        if (entities.size() != request.getSectionIds().size()) {
            throw new SectionNotFoundException("Some sections not found");
        }

        LocalDateTime now = LocalDateTime.now();
        entities.forEach(entity -> {
            if (request.getEnabled() != null) {
                entity.setEnabled(request.getEnabled());
            }
            if (request.getPersonalized() != null) {
                entity.setPersonalized(request.getPersonalized());
            }
            if (request.getDisplayOrder() != null) {
                entity.setDisplayOrder(request.getDisplayOrder());
            }
            entity.setUpdatedAt(now);
        });

        List<CatalogSectionEntity> updated = catalogSectionRepository.saveAll(entities);
        return catalogSectionMapper.toDtoList(updated);
    }

    public CatalogSectionDto enableDisable(UUID sectionId, boolean enabled) {
        CatalogSectionEntity entity = catalogSectionRepository.findById(sectionId)
                .orElseThrow(() -> new SectionNotFoundException("Section not found with id: " + sectionId));

        entity.setEnabled(enabled);
        entity.setUpdatedAt(LocalDateTime.now());

        CatalogSectionEntity updated = catalogSectionRepository.save(entity);
        return catalogSectionMapper.toDto(updated);
    }

    public CatalogSectionDto toggleSection(UUID sectionId) {
        CatalogSectionEntity entity = catalogSectionRepository.findById(sectionId)
                .orElseThrow(() -> new SectionNotFoundException("Section not found with id: " + sectionId));

        boolean newStatus = !entity.isEnabled();
        entity.setEnabled(newStatus);
        entity.setUpdatedAt(LocalDateTime.now());

        CatalogSectionEntity updated = catalogSectionRepository.save(entity);
        return catalogSectionMapper.toDto(updated);
    }
}

