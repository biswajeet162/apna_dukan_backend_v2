package com.apna_dukan_backend.catalog.variant.service;

import com.apna_dukan_backend.catalog.product.exception.ProductNotFoundException;
import com.apna_dukan_backend.catalog.product.repository.ProductRepository;
import com.apna_dukan_backend.catalog.variant.exception.VariantNotFoundException;
import com.apna_dukan_backend.catalog.variant.model.VariantEntity;
import com.apna_dukan_backend.catalog.variant.model.dto.VariantAdminResponseDto;
import com.apna_dukan_backend.catalog.variant.repository.VariantRepository;
import com.apna_dukan_backend.catalog.variant.service.mapper.VariantMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class VariantQueryService {
    private final VariantRepository variantRepository;
    private final ProductRepository productRepository;
    private final VariantMapper variantMapper;

    public VariantQueryService(VariantRepository variantRepository,
                               ProductRepository productRepository,
                               VariantMapper variantMapper) {
        this.variantRepository = variantRepository;
        this.productRepository = productRepository;
        this.variantMapper = variantMapper;
    }

    @Transactional(readOnly = true)
    public List<VariantAdminResponseDto> getAllVariants() {
        List<VariantEntity> entities = variantRepository.findAll();
        return variantMapper.toAdminDtoList(entities);
    }

    @Transactional(readOnly = true)
    public VariantAdminResponseDto getVariantById(UUID variantId) {
        VariantEntity entity = variantRepository.findById(variantId)
                .orElseThrow(() -> new VariantNotFoundException("Variant not found with id: " + variantId));
        return variantMapper.toAdminDto(entity);
    }

    @Transactional(readOnly = true)
    public List<VariantAdminResponseDto> getVariantsByProductId(UUID productId) {
        // Validate product exists
        productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        // Fetch all variants for the product (enabled and disabled)
        List<VariantEntity> entities = variantRepository.findByProductId(productId);
        return variantMapper.toAdminDtoList(entities);
    }
}

