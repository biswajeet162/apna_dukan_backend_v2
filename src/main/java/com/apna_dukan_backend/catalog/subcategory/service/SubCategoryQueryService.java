package com.apna_dukan_backend.catalog.subcategory.service;

import com.apna_dukan_backend.catalog.category.exception.CategoryNotFoundException;
import com.apna_dukan_backend.catalog.category.model.CategoryEntity;
import com.apna_dukan_backend.catalog.category.repository.CategoryRepository;
import com.apna_dukan_backend.catalog.subcategory.dto.SubCategoryAdminDto;
import com.apna_dukan_backend.catalog.subcategory.dto.SubCategoryAdminResponseDto;
import com.apna_dukan_backend.catalog.subcategory.dto.SubCategoryDto;
import com.apna_dukan_backend.catalog.subcategory.dto.SubCategoryResponseDto;
import com.apna_dukan_backend.catalog.subcategory.mapper.SubCategoryMapper;
import com.apna_dukan_backend.catalog.subcategory.model.SubCategoryEntity;
import com.apna_dukan_backend.catalog.subcategory.repository.SubCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SubCategoryQueryService {
    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;
    private final SubCategoryMapper subCategoryMapper;

    public SubCategoryQueryService(SubCategoryRepository subCategoryRepository,
                                  CategoryRepository categoryRepository,
                                  SubCategoryMapper subCategoryMapper) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryRepository = categoryRepository;
        this.subCategoryMapper = subCategoryMapper;
    }

    public SubCategoryAdminResponseDto getSubCategoriesForAdmin(UUID categoryId) {
        CategoryEntity category = validateCategory(categoryId);

        List<SubCategoryEntity> entities = subCategoryRepository.findByCategoryIdOrderByDisplayOrderAsc(categoryId);
        List<SubCategoryAdminDto> adminDtos = subCategoryMapper.toAdminDtoList(entities);

        return new SubCategoryAdminResponseDto(
                categoryId,
                category.getName(),
                adminDtos != null ? adminDtos : List.of()
        );
    }

    public SubCategoryResponseDto getSubCategoriesForUser(UUID categoryId) {
        CategoryEntity category = validateCategory(categoryId);

        List<SubCategoryEntity> entities = subCategoryRepository.findByCategoryIdAndEnabledTrueOrderByDisplayOrderAsc(categoryId);
        List<SubCategoryDto> subCategories = subCategoryMapper.toDtoList(entities);

        return new SubCategoryResponseDto(
                categoryId,
                category.getName(),
                subCategories != null ? subCategories : List.of()
        );
    }

    public SubCategoryAdminDto getSubCategoryById(UUID subCategoryId) {
        SubCategoryEntity entity = subCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new com.apna_dukan_backend.catalog.subcategory.exception.SubCategoryNotFoundException(
                        "SubCategory not found with id: " + subCategoryId));
        return subCategoryMapper.toAdminDto(entity);
    }

    private CategoryEntity validateCategory(UUID categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + categoryId));
    }
}

