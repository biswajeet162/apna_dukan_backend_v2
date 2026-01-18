package com.apna_dukan_backend.catalog.category.service;

import com.apna_dukan_backend.catalog.category.dto.CategoryAdminDto;
import com.apna_dukan_backend.catalog.category.dto.CategoryDto;
import com.apna_dukan_backend.catalog.category.dto.CategorySectionAdminResponseDto;
import com.apna_dukan_backend.catalog.category.dto.CategorySectionResponseDto;
import com.apna_dukan_backend.catalog.category.exception.InvalidSectionException;
import com.apna_dukan_backend.catalog.category.mapper.CategoryMapper;
import com.apna_dukan_backend.catalog.category.model.CategoryEntity;
import com.apna_dukan_backend.catalog.category.repository.CategoryRepository;
import com.apna_dukan_backend.catalog.layout.exception.SectionNotFoundException;
import com.apna_dukan_backend.catalog.layout.model.CatalogSectionEntity;
import com.apna_dukan_backend.catalog.layout.repository.CatalogSectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryQueryService {
    private static final String PRODUCT_CATEGORY_SECTION_CODE = "PRODUCT_CATEGORY";

    private final CategoryRepository categoryRepository;
    private final CatalogSectionRepository catalogSectionRepository;
    private final CategoryMapper categoryMapper;

    public CategoryQueryService(CategoryRepository categoryRepository,
                               CatalogSectionRepository catalogSectionRepository,
                               CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.catalogSectionRepository = catalogSectionRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategorySectionAdminResponseDto getCategoriesForAdmin(UUID sectionId) {
        CatalogSectionEntity section = validateSection(sectionId);
        validateSectionCode(section);

        List<CategoryEntity> entities = categoryRepository.findBySectionIdOrderByDisplayOrderAsc(sectionId);
        List<CategoryAdminDto> categories = categoryMapper.toAdminDtoList(entities);

        return new CategorySectionAdminResponseDto(
                sectionId,
                section.getSectionCode(),
                categories != null ? categories : List.of()
        );
    }

    public CategorySectionResponseDto getCategoriesForUser(UUID sectionId) {
        CatalogSectionEntity section = validateSection(sectionId);
        validateSectionCode(section);

        List<CategoryEntity> entities = categoryRepository.findBySectionIdAndEnabledTrueOrderByDisplayOrderAsc(sectionId);
        List<CategoryDto> categories = categoryMapper.toDtoList(entities);

        return new CategorySectionResponseDto(
                sectionId,
                section.getSectionCode(),
                categories != null ? categories : List.of()
        );
    }

    private CatalogSectionEntity validateSection(UUID sectionId) {
        return catalogSectionRepository.findById(sectionId)
                .orElseThrow(() -> new SectionNotFoundException("Section not found with id: " + sectionId));
    }

    private void validateSectionCode(CatalogSectionEntity section) {
        if (!PRODUCT_CATEGORY_SECTION_CODE.equals(section.getSectionCode())) {
            throw new InvalidSectionException("Invalid section code. Expected PRODUCT_CATEGORY, but found: " + section.getSectionCode());
        }
    }
}

