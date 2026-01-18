package com.apna_dukan_backend.catalog.variant.service;

import com.apna_dukan_backend.catalog.product.exception.ProductNotFoundException;
import com.apna_dukan_backend.catalog.product.model.ProductEntity;
import com.apna_dukan_backend.catalog.product.repository.ProductRepository;
import com.apna_dukan_backend.catalog.variant.exception.DuplicateSkuException;
import com.apna_dukan_backend.catalog.variant.exception.VariantNotFoundException;
import com.apna_dukan_backend.catalog.variant.model.VariantEntity;
import com.apna_dukan_backend.catalog.variant.model.dto.VariantAdminResponseDto;
import com.apna_dukan_backend.catalog.variant.model.dto.VariantCreateRequestDto;
import com.apna_dukan_backend.catalog.variant.model.dto.VariantUpdateRequestDto;
import com.apna_dukan_backend.catalog.variant.repository.VariantRepository;
import com.apna_dukan_backend.catalog.variant.service.mapper.VariantMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class VariantCommandService {
    private final VariantRepository variantRepository;
    private final ProductRepository productRepository;
    private final VariantMapper variantMapper;

    public VariantCommandService(VariantRepository variantRepository,
                                 ProductRepository productRepository,
                                 VariantMapper variantMapper) {
        this.variantRepository = variantRepository;
        this.productRepository = productRepository;
        this.variantMapper = variantMapper;
    }

    public VariantAdminResponseDto createVariant(UUID productId, VariantCreateRequestDto request) {
        // Validate product exists
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        // Validate SKU uniqueness
        if (variantRepository.findBySku(request.getSku()).isPresent()) {
            throw new DuplicateSkuException("SKU already exists: " + request.getSku());
        }

        // Create variant entity
        VariantEntity entity = new VariantEntity();
        entity.setVariantId(UUID.randomUUID());
        entity.setProductId(productId);
        entity.setSku(request.getSku());
        entity.setLabel(request.getLabel());
        entity.setAttributes(request.getAttributes());
        entity.setEnabled(false); // Default to false as per requirements
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());

        VariantEntity saved = variantRepository.save(entity);
        return variantMapper.toAdminDto(saved);
    }

    public VariantAdminResponseDto updateVariant(UUID variantId, VariantUpdateRequestDto request) {
        VariantEntity entity = variantRepository.findById(variantId)
                .orElseThrow(() -> new VariantNotFoundException("Variant not found with id: " + variantId));

        // Update only provided fields
        if (request.getLabel() != null) {
            entity.setLabel(request.getLabel());
        }
        if (request.getAttributes() != null) {
            entity.setAttributes(request.getAttributes());
        }
        entity.setUpdatedAt(LocalDateTime.now());

        VariantEntity updated = variantRepository.save(entity);
        return variantMapper.toAdminDto(updated);
    }

    public VariantAdminResponseDto updateVariantEnabledStatus(UUID variantId, boolean enabled) {
        VariantEntity entity = variantRepository.findById(variantId)
                .orElseThrow(() -> new VariantNotFoundException("Variant not found with id: " + variantId));

        entity.setEnabled(enabled);
        entity.setUpdatedAt(LocalDateTime.now());

        VariantEntity updated = variantRepository.save(entity);
        return variantMapper.toAdminDto(updated);
    }
}


