package com.apna_dukan_backend.catalog.productgroup.service;

import com.apna_dukan_backend.catalog.productgroup.dto.BulkUpdateProductGroupItem;
import com.apna_dukan_backend.catalog.productgroup.dto.BulkUpdateProductGroupRequest;
import com.apna_dukan_backend.catalog.productgroup.dto.CreateProductGroupRequest;
import com.apna_dukan_backend.catalog.productgroup.dto.ProductGroupAdminDto;
import com.apna_dukan_backend.catalog.productgroup.dto.UpdateProductGroupRequest;
import com.apna_dukan_backend.catalog.productgroup.exception.ProductGroupNotFoundException;
import com.apna_dukan_backend.catalog.productgroup.mapper.ProductGroupMapper;
import com.apna_dukan_backend.catalog.productgroup.model.ProductGroupEntity;
import com.apna_dukan_backend.catalog.productgroup.repository.ProductGroupRepository;
import com.apna_dukan_backend.catalog.subcategory.exception.SubCategoryNotFoundException;
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
public class ProductGroupCommandService {
    private final ProductGroupRepository productGroupRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ProductGroupMapper productGroupMapper;

    public ProductGroupCommandService(ProductGroupRepository productGroupRepository,
                                    SubCategoryRepository subCategoryRepository,
                                    ProductGroupMapper productGroupMapper) {
        this.productGroupRepository = productGroupRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.productGroupMapper = productGroupMapper;
    }

    public ProductGroupAdminDto createProductGroup(CreateProductGroupRequest request) {
        // Validate subCategory exists
        SubCategoryEntity subCategory = subCategoryRepository.findById(request.getSubCategoryId())
                .orElseThrow(() -> new SubCategoryNotFoundException("SubCategory not found with id: " + request.getSubCategoryId()));

        ProductGroupEntity entity = new ProductGroupEntity();
        entity.setProductGroupId(UUID.randomUUID());
        entity.setSubCategoryId(request.getSubCategoryId());
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setCode(request.getCode());
        entity.setDisplayOrder(request.getDisplayOrder());
        entity.setEnabled(request.getEnabled() != null ? request.getEnabled() : true);
        entity.setImageUrls(request.getImageUrl());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());

        ProductGroupEntity saved = productGroupRepository.save(entity);
        return productGroupMapper.toAdminDto(saved);
    }

    public ProductGroupAdminDto updateProductGroup(UUID productGroupId, UpdateProductGroupRequest request) {
        ProductGroupEntity entity = productGroupRepository.findById(productGroupId)
                .orElseThrow(() -> new ProductGroupNotFoundException("ProductGroup not found with id: " + productGroupId));

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

        ProductGroupEntity updated = productGroupRepository.save(entity);
        return productGroupMapper.toAdminDto(updated);
    }

    public void deleteProductGroup(UUID productGroupId) {
        if (!productGroupRepository.existsById(productGroupId)) {
            throw new ProductGroupNotFoundException("ProductGroup not found with id: " + productGroupId);
        }
        productGroupRepository.deleteById(productGroupId);
    }

    public List<ProductGroupAdminDto> bulkUpdate(BulkUpdateProductGroupRequest request) {
        List<UUID> productGroupIds = request.getProductGroups().stream()
                .map(BulkUpdateProductGroupItem::getProductGroupId)
                .toList();

        List<ProductGroupEntity> entities = productGroupRepository.findAllById(productGroupIds);

        if (entities.size() != productGroupIds.size()) {
            throw new ProductGroupNotFoundException("Some product groups not found");
        }

        // Create a map for quick lookup
        Map<UUID, ProductGroupEntity> entityMap = entities.stream()
                .collect(Collectors.toMap(ProductGroupEntity::getProductGroupId, e -> e));

        LocalDateTime now = LocalDateTime.now();

        // Update each entity based on the corresponding BulkUpdateProductGroupItem
        request.getProductGroups().forEach(item -> {
            ProductGroupEntity entity = entityMap.get(item.getProductGroupId());
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

        List<ProductGroupEntity> updated = productGroupRepository.saveAll(entities);
        return productGroupMapper.toAdminDtoList(updated);
    }
}

