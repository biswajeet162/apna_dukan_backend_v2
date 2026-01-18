package com.apna_dukan_backend.catalog.productgroup.service;

import com.apna_dukan_backend.catalog.productgroup.dto.ProductGroupAdminDto;
import com.apna_dukan_backend.catalog.productgroup.dto.ProductGroupAdminResponseDto;
import com.apna_dukan_backend.catalog.productgroup.dto.ProductGroupDto;
import com.apna_dukan_backend.catalog.productgroup.dto.ProductGroupResponseDto;
import com.apna_dukan_backend.catalog.productgroup.mapper.ProductGroupMapper;
import com.apna_dukan_backend.catalog.productgroup.model.ProductGroupEntity;
import com.apna_dukan_backend.catalog.productgroup.repository.ProductGroupRepository;
import com.apna_dukan_backend.catalog.subcategory.exception.SubCategoryNotFoundException;
import com.apna_dukan_backend.catalog.subcategory.model.SubCategoryEntity;
import com.apna_dukan_backend.catalog.subcategory.repository.SubCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductGroupQueryService {
    private final ProductGroupRepository productGroupRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ProductGroupMapper productGroupMapper;

    public ProductGroupQueryService(ProductGroupRepository productGroupRepository,
                                   SubCategoryRepository subCategoryRepository,
                                   ProductGroupMapper productGroupMapper) {
        this.productGroupRepository = productGroupRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.productGroupMapper = productGroupMapper;
    }

    public ProductGroupAdminResponseDto getProductGroupsForAdmin(UUID subCategoryId) {
        SubCategoryEntity subCategory = validateSubCategory(subCategoryId);

        List<ProductGroupEntity> entities = productGroupRepository.findBySubCategoryIdOrderByDisplayOrderAsc(subCategoryId);
        List<ProductGroupAdminDto> productGroups = productGroupMapper.toAdminDtoList(entities);

        return new ProductGroupAdminResponseDto(
                subCategoryId,
                subCategory.getName(),
                productGroups != null ? productGroups : List.of()
        );
    }

    public ProductGroupResponseDto getProductGroupsForUser(UUID subCategoryId) {
        SubCategoryEntity subCategory = validateSubCategory(subCategoryId);

        List<ProductGroupEntity> entities = productGroupRepository.findBySubCategoryIdAndEnabledTrueOrderByDisplayOrderAsc(subCategoryId);
        List<ProductGroupDto> productGroups = productGroupMapper.toDtoList(entities);

        return new ProductGroupResponseDto(
                subCategoryId,
                subCategory.getName(),
                productGroups != null ? productGroups : List.of()
        );
    }

    private SubCategoryEntity validateSubCategory(UUID subCategoryId) {
        return subCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new SubCategoryNotFoundException("SubCategory not found with id: " + subCategoryId));
    }
}

