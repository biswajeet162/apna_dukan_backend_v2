package com.apna_dukan_backend.catalog.category.service;

import com.apna_dukan_backend.catalog.category.dto.BulkUpdateCategoryItem;
import com.apna_dukan_backend.catalog.category.dto.BulkUpdateCategoryRequest;
import com.apna_dukan_backend.catalog.category.dto.CategoryAdminDto;
import com.apna_dukan_backend.catalog.category.dto.CreateCategoryRequest;
import com.apna_dukan_backend.catalog.category.dto.UpdateCategoryRequest;
import com.apna_dukan_backend.catalog.category.exception.CategoryNotFoundException;
import com.apna_dukan_backend.catalog.category.exception.InvalidSectionException;
import com.apna_dukan_backend.catalog.category.mapper.CategoryMapper;
import com.apna_dukan_backend.catalog.category.model.CategoryEntity;
import com.apna_dukan_backend.catalog.category.repository.CategoryRepository;
import com.apna_dukan_backend.catalog.layout.exception.SectionNotFoundException;
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
public class CategoryCommandService {
    private static final String PRODUCT_CATEGORY_SECTION_CODE = "PRODUCT_CATEGORY";

    private final CategoryRepository categoryRepository;
    private final CatalogSectionRepository catalogSectionRepository;
    private final CategoryMapper categoryMapper;

    public CategoryCommandService(CategoryRepository categoryRepository,
                                 CatalogSectionRepository catalogSectionRepository,
                                 CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.catalogSectionRepository = catalogSectionRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryAdminDto createCategory(CreateCategoryRequest request) {
        // Validate section exists and has correct section code
        CatalogSectionEntity section = catalogSectionRepository.findById(request.getSectionId())
                .orElseThrow(() -> new SectionNotFoundException("Section not found with id: " + request.getSectionId()));

        if (!PRODUCT_CATEGORY_SECTION_CODE.equals(section.getSectionCode())) {
            throw new InvalidSectionException("Invalid section code. Expected PRODUCT_CATEGORY, but found: " + section.getSectionCode());
        }

        CategoryEntity entity = new CategoryEntity();
        entity.setCategoryId(UUID.randomUUID());
        entity.setSectionId(request.getSectionId());
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setCode(request.getCode());
        entity.setDisplayOrder(request.getDisplayOrder());
        entity.setEnabled(request.getEnabled() != null ? request.getEnabled() : true);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());

        CategoryEntity saved = categoryRepository.save(entity);
        return categoryMapper.toAdminDto(saved);
    }

    public CategoryAdminDto updateCategory(UUID categoryId, UpdateCategoryRequest request) {
        CategoryEntity entity = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + categoryId));

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
        entity.setUpdatedAt(LocalDateTime.now());

        CategoryEntity updated = categoryRepository.save(entity);
        return categoryMapper.toAdminDto(updated);
    }

    public void deleteCategory(UUID categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new CategoryNotFoundException("Category not found with id: " + categoryId);
        }
        categoryRepository.deleteById(categoryId);
    }

    public List<CategoryAdminDto> bulkUpdate(BulkUpdateCategoryRequest request) {
        List<UUID> categoryIds = request.getCategories().stream()
                .map(BulkUpdateCategoryItem::getCategoryId)
                .toList();

        List<CategoryEntity> entities = categoryRepository.findAllById(categoryIds);

        if (entities.size() != categoryIds.size()) {
            throw new CategoryNotFoundException("Some categories not found");
        }

        // Create a map for quick lookup
        Map<UUID, CategoryEntity> entityMap = entities.stream()
                .collect(Collectors.toMap(CategoryEntity::getCategoryId, e -> e));

        LocalDateTime now = LocalDateTime.now();

        // Update each entity based on the corresponding BulkUpdateCategoryItem
        request.getCategories().forEach(item -> {
            CategoryEntity entity = entityMap.get(item.getCategoryId());
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
                entity.setUpdatedAt(now);
            }
        });

        List<CategoryEntity> updated = categoryRepository.saveAll(entities);
        return categoryMapper.toAdminDtoList(updated);
    }
}
