package com.apna_dukan_backend.catalog.product.service;

import com.apna_dukan_backend.catalog.product.exception.DuplicateProductCodeException;
import com.apna_dukan_backend.catalog.product.exception.ProductNotFoundException;
import com.apna_dukan_backend.catalog.product.model.ProductEntity;
import com.apna_dukan_backend.catalog.product.model.dto.ProductAdminResponseDto;
import com.apna_dukan_backend.catalog.product.model.dto.ProductCreateRequestDto;
import com.apna_dukan_backend.catalog.product.model.dto.ProductUpdateRequestDto;
import com.apna_dukan_backend.catalog.product.repository.ProductRepository;
import com.apna_dukan_backend.catalog.product.service.mapper.ProductMapper;
import com.apna_dukan_backend.catalog.productgroup.exception.ProductGroupNotFoundException;
import com.apna_dukan_backend.catalog.productgroup.model.ProductGroupEntity;
import com.apna_dukan_backend.catalog.productgroup.repository.ProductGroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class ProductCommandService {
    private final ProductRepository productRepository;
    private final ProductGroupRepository productGroupRepository;
    private final ProductMapper productMapper;

    public ProductCommandService(ProductRepository productRepository,
                                 ProductGroupRepository productGroupRepository,
                                 ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productGroupRepository = productGroupRepository;
        this.productMapper = productMapper;
    }

    public ProductAdminResponseDto createProduct(ProductCreateRequestDto request) {
        // Validate productGroup exists
        ProductGroupEntity productGroup = productGroupRepository.findById(request.getProductGroupId())
                .orElseThrow(() -> new ProductGroupNotFoundException("ProductGroup not found with id: " + request.getProductGroupId()));

        // Validate code uniqueness
        if (productRepository.findByCode(request.getCode()).isPresent()) {
            throw new DuplicateProductCodeException("Product code already exists: " + request.getCode());
        }

        // Create product entity
        ProductEntity entity = new ProductEntity();
        entity.setProductId(UUID.randomUUID());
        entity.setProductGroupId(request.getProductGroupId());
        entity.setName(request.getName());
        entity.setBrand(request.getBrand());
        entity.setDescription(request.getDescription());
        entity.setCode(request.getCode());
        entity.setPrimaryImageUrl(request.getPrimaryImageUrl());
        entity.setImageUrls(request.getImageUrls());
        entity.setEnabled(false); // Default to false as per requirements
        entity.setDisplayOrder(0); // Default value, can be updated later if needed
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());

        ProductEntity saved = productRepository.save(entity);
        return productMapper.toAdminDto(saved);
    }

    public ProductAdminResponseDto updateProduct(UUID productId, ProductUpdateRequestDto request) {
        ProductEntity entity = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        // Update only provided fields
        if (request.getName() != null) {
            entity.setName(request.getName());
        }
        if (request.getBrand() != null) {
            entity.setBrand(request.getBrand());
        }
        if (request.getDescription() != null) {
            entity.setDescription(request.getDescription());
        }
        if (request.getPrimaryImageUrl() != null) {
            entity.setPrimaryImageUrl(request.getPrimaryImageUrl());
        }
        if (request.getImageUrls() != null) {
            entity.setImageUrls(request.getImageUrls());
        }
        entity.setUpdatedAt(LocalDateTime.now());

        ProductEntity updated = productRepository.save(entity);
        return productMapper.toAdminDto(updated);
    }

    public ProductAdminResponseDto updateProductEnabledStatus(UUID productId, boolean enabled) {
        ProductEntity entity = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        entity.setEnabled(enabled);
        entity.setUpdatedAt(LocalDateTime.now());

        ProductEntity updated = productRepository.save(entity);
        return productMapper.toAdminDto(updated);
    }
}




