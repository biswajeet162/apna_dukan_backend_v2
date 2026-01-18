package com.apna_dukan_backend.catalog.product.service;

import com.apna_dukan_backend.catalog.product.exception.ProductNotFoundException;
import com.apna_dukan_backend.catalog.product.model.ProductEntity;
import com.apna_dukan_backend.catalog.product.model.dto.ProductDetailsDto;
import com.apna_dukan_backend.catalog.product.repository.ProductRepository;
import com.apna_dukan_backend.catalog.product.service.assembler.ProductDetailsAssembler;
import com.apna_dukan_backend.catalog.variant.model.VariantEntity;
import com.apna_dukan_backend.catalog.variant.repository.VariantRepository;
import com.apna_dukan_backend.inventory.model.InventoryEntity;
import com.apna_dukan_backend.inventory.repository.InventoryRepository;
import com.apna_dukan_backend.catalog.pricing.model.PricingEntity;
import com.apna_dukan_backend.catalog.pricing.service.PricingQueryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductDetailsQueryService {
    private final ProductRepository productRepository;
    private final VariantRepository variantRepository;
    private final PricingQueryService pricingQueryService;
    private final InventoryRepository inventoryRepository;
    private final ProductDetailsAssembler assembler;

    public ProductDetailsQueryService(
            ProductRepository productRepository,
            VariantRepository variantRepository,
            PricingQueryService pricingQueryService,
            InventoryRepository inventoryRepository,
            ProductDetailsAssembler assembler) {
        this.productRepository = productRepository;
        this.variantRepository = variantRepository;
        this.pricingQueryService = pricingQueryService;
        this.inventoryRepository = inventoryRepository;
        this.assembler = assembler;
    }

    @Transactional(readOnly = true)
    public ProductDetailsDto getProductDetails(UUID productId) {
        // 1. Validate product exists and is enabled
        ProductEntity product = productRepository.findByProductIdAndEnabledTrue(productId)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found or disabled with id: " + productId));

        // 2. Fetch all enabled variants for product
        List<VariantEntity> variants = variantRepository
                .findByProductIdAndEnabledTrue(productId);

        if (variants.isEmpty()) {
            // Return product with empty variants list
            return assembler.assemble(product, Collections.emptyList(), 
                    Collections.emptyMap(), Collections.emptyMap());
        }

        // 3. Batch fetch pricing for all variants
        List<UUID> variantIds = variants.stream()
                .map(VariantEntity::getVariantId)
                .collect(Collectors.toList());

        Map<UUID, PricingEntity> pricingMap = pricingQueryService.getActivePricingByVariantIds(variantIds);

        // 4. Batch fetch inventory for all variants
        List<InventoryEntity> inventories = inventoryRepository
                .findByVariantIdIn(variantIds);

        Map<UUID, InventoryEntity> inventoryMap = inventories.stream()
                .collect(Collectors.toMap(InventoryEntity::getVariantId, i -> i));

        // 5. Assemble ProductDetailsDto
        // Variants without pricing will be skipped in the assembler
        return assembler.assemble(product, variants, pricingMap, inventoryMap);
    }
}

