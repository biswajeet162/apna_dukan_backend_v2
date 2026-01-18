package com.apna_dukan_backend.catalog.layout.service;

import com.apna_dukan_backend.catalog.layout.dto.BulkUpdateItem;
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
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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
        List<UUID> sectionIds = request.getSections().stream()
                .map(BulkUpdateItem::getSectionId)
                .toList();
        
        List<CatalogSectionEntity> entities = catalogSectionRepository.findAllById(sectionIds);
        
        if (entities.size() != sectionIds.size()) {
            throw new SectionNotFoundException("Some sections not found");
        }

        // Create a map for quick lookup
        Map<UUID, CatalogSectionEntity> entityMap = entities.stream()
                .collect(Collectors.toMap(CatalogSectionEntity::getSectionId, e -> e));

        LocalDateTime now = LocalDateTime.now();
        
        // Update each entity based on the corresponding BulkUpdateItem
        request.getSections().forEach(item -> {
            CatalogSectionEntity entity = entityMap.get(item.getSectionId());
            if (entity != null) {
                if (item.getSectionCode() != null) {
                    entity.setSectionCode(item.getSectionCode());
                }
                if (item.getTitle() != null) {
                    entity.setTitle(item.getTitle());
                }
                if (item.getDescription() != null) {
                    entity.setDescription(item.getDescription());
                }
                if (item.getLayoutType() != null) {
                    entity.setLayoutType(item.getLayoutType());
                }
                if (item.getScrollType() != null) {
                    entity.setScrollType(item.getScrollType());
                }
                if (item.getDisplayOrder() != null) {
                    entity.setDisplayOrder(item.getDisplayOrder());
                }
                if (item.getEnabled() != null) {
                    entity.setEnabled(item.getEnabled());
                }
                if (item.getPersonalized() != null) {
                    entity.setPersonalized(item.getPersonalized());
                }
                entity.setUpdatedAt(now);
            }
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
}

