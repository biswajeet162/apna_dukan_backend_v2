package com.apna_dukan_backend.catalog.subcategory.service;

import com.apna_dukan_backend.catalog.category.exception.CategoryNotFoundException;
import com.apna_dukan_backend.catalog.category.model.CategoryEntity;
import com.apna_dukan_backend.catalog.category.repository.CategoryRepository;
import com.apna_dukan_backend.catalog.subcategory.dto.BulkUpdateSubCategoryItem;
import com.apna_dukan_backend.catalog.subcategory.dto.BulkUpdateSubCategoryRequest;
import com.apna_dukan_backend.catalog.subcategory.dto.CreateSubCategoryRequest;
import com.apna_dukan_backend.catalog.subcategory.dto.SubCategoryAdminDto;
import com.apna_dukan_backend.catalog.subcategory.dto.UpdateSubCategoryRequest;
import com.apna_dukan_backend.catalog.subcategory.exception.SubCategoryNotFoundException;
import com.apna_dukan_backend.catalog.subcategory.mapper.SubCategoryMapper;
import com.apna_dukan_backend.catalog.subcategory.model.SubCategoryEntity;
import com.apna_dukan_backend.catalog.subcategory.repository.SubCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubCategoryCommandService {
    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;
    private final SubCategoryMapper subCategoryMapper;

    public SubCategoryCommandService(SubCategoryRepository subCategoryRepository,
                                    CategoryRepository categoryRepository,
                                    SubCategoryMapper subCategoryMapper) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryRepository = categoryRepository;
        this.subCategoryMapper = subCategoryMapper;
    }

    public SubCategoryAdminDto createSubCategory(CreateSubCategoryRequest request) {
        // Validate category exists
        CategoryEntity category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + request.getCategoryId()));

        SubCategoryEntity entity = new SubCategoryEntity();
        entity.setSubCategoryId(UUID.randomUUID());
        entity.setCategoryId(request.getCategoryId());
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setCode(request.getCode());
        entity.setDisplayOrder(request.getDisplayOrder());
        entity.setEnabled(request.getEnabled() != null ? request.getEnabled() : true);
        entity.setImageUrls(request.getImageUrl());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());

        SubCategoryEntity saved = subCategoryRepository.save(entity);
        return subCategoryMapper.toAdminDto(saved);
    }

    public SubCategoryAdminDto updateSubCategory(UUID subCategoryId, UpdateSubCategoryRequest request) {
        SubCategoryEntity entity = subCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new SubCategoryNotFoundException("SubCategory not found with id: " + subCategoryId));

        if (request.getName() != null) {
            entity.setName(request.getName());
        }
        if (request.getDescription() != null) {
            entity.setDescription(request.getDescription());
        }
        if (request.getCode() != null) {
            entity.setCode(request.getCode());
        }
        if (request.getDisplayOrder() != null) {
            entity.setDisplayOrder(request.getDisplayOrder());
        }
        if (request.getEnabled() != null) {
            entity.setEnabled(request.getEnabled());
        }
        if (request.getImageUrl() != null) {
            entity.setImageUrls(request.getImageUrl());
        }
        entity.setUpdatedAt(LocalDateTime.now());

        SubCategoryEntity updated = subCategoryRepository.save(entity);
        return subCategoryMapper.toAdminDto(updated);
    }

    public void deleteSubCategory(UUID subCategoryId) {
        if (!subCategoryRepository.existsById(subCategoryId)) {
            throw new SubCategoryNotFoundException("SubCategory not found with id: " + subCategoryId);
        }
        subCategoryRepository.deleteById(subCategoryId);
    }

    public List<SubCategoryAdminDto> bulkUpdate(BulkUpdateSubCategoryRequest request) {
        List<UUID> subCategoryIds = request.getSubCategories().stream()
                .map(BulkUpdateSubCategoryItem::getSubCategoryId)
                .toList();

        List<SubCategoryEntity> entities = subCategoryRepository.findAllById(subCategoryIds);

        if (entities.size() != subCategoryIds.size()) {
            throw new SubCategoryNotFoundException("Some subcategories not found");
        }

        // Create a map for quick lookup
        Map<UUID, SubCategoryEntity> entityMap = entities.stream()
                .collect(Collectors.toMap(SubCategoryEntity::getSubCategoryId, e -> e));

        LocalDateTime now = LocalDateTime.now();

        // Update each entity based on the corresponding BulkUpdateSubCategoryItem
        request.getSubCategories().forEach(item -> {
            SubCategoryEntity entity = entityMap.get(item.getSubCategoryId());
            if (entity != null) {
                if (item.getName() != null) {
                    entity.setName(item.getName());
                }
                if (item.getDescription() != null) {
                    entity.setDescription(item.getDescription());
                }
                if (item.getCode() != null) {
                    entity.setCode(item.getCode());
                }
                if (item.getDisplayOrder() != null) {
                    entity.setDisplayOrder(item.getDisplayOrder());
                }
                if (item.getEnabled() != null) {
                    entity.setEnabled(item.getEnabled());
                }
                if (item.getImageUrl() != null) {
                    entity.setImageUrls(item.getImageUrl());
                }
                entity.setUpdatedAt(now);
            }
        });

        List<SubCategoryEntity> updated = subCategoryRepository.saveAll(entities);
        return subCategoryMapper.toAdminDtoList(updated);
    }
}


