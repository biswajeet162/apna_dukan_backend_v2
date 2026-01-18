package com.apna_dukan_backend.catalog.product.service;

import com.apna_dukan_backend.catalog.product.model.ProductEntity;
import com.apna_dukan_backend.catalog.product.model.dto.ProductListingResponseDto;
import com.apna_dukan_backend.catalog.product.repository.ProductRepository;
import com.apna_dukan_backend.catalog.product.service.assembler.ProductListingAssembler;
import com.apna_dukan_backend.catalog.productgroup.exception.ProductGroupNotFoundException;
import com.apna_dukan_backend.catalog.productgroup.model.ProductGroupEntity;
import com.apna_dukan_backend.catalog.productgroup.repository.ProductGroupRepository;
import com.apna_dukan_backend.catalog.variant.model.VariantEntity;
import com.apna_dukan_backend.catalog.variant.repository.VariantRepository;
import com.apna_dukan_backend.inventory.model.InventoryEntity;
import com.apna_dukan_backend.inventory.repository.InventoryRepository;
import com.apna_dukan_backend.pricing.model.PricingEntity;
import com.apna_dukan_backend.pricing.repository.PricingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductListingQueryService {
    private final ProductRepository productRepository;
    private final ProductGroupRepository productGroupRepository;
    private final VariantRepository variantRepository;
    private final PricingRepository pricingRepository;
    private final InventoryRepository inventoryRepository;
    private final ProductListingAssembler assembler;

    public ProductListingQueryService(
            ProductRepository productRepository,
            ProductGroupRepository productGroupRepository,
            VariantRepository variantRepository,
            PricingRepository pricingRepository,
            InventoryRepository inventoryRepository,
            ProductListingAssembler assembler) {
        this.productRepository = productRepository;
        this.productGroupRepository = productGroupRepository;
        this.variantRepository = variantRepository;
        this.pricingRepository = pricingRepository;
        this.inventoryRepository = inventoryRepository;
        this.assembler = assembler;
    }

    @Transactional(readOnly = true)
    public ProductListingResponseDto getProductListing(UUID productGroupId, int page, int size) {
        // 1. Validate productGroup exists
        ProductGroupEntity productGroup = productGroupRepository.findById(productGroupId)
                .orElseThrow(() -> new ProductGroupNotFoundException(
                        "ProductGroup not found with id: " + productGroupId));

        // 2. Validate and set default pagination parameters
        if (page < 0) {
            page = 0;
        }
        if (size <= 0) {
            size = 20; // Default page size
        }
        if (size > 100) {
            size = 100; // Max page size
        }

        Pageable pageable = PageRequest.of(page, size);

        // 3. Fetch enabled products by productGroupId, sorted by displayOrder with pagination
        Page<ProductEntity> productPage = productRepository
                .findByProductGroupIdAndEnabledTrueOrderByDisplayOrderAsc(productGroupId, pageable);

        List<ProductEntity> products = productPage.getContent();

        if (products.isEmpty()) {
            ProductListingResponseDto.PaginationMetadata pagination = new ProductListingResponseDto.PaginationMetadata(
                    page,
                    size,
                    0,
                    0,
                    false,
                    false
            );
            return new ProductListingResponseDto(
                    productGroupId,
                    productGroup.getName(),
                    Collections.emptyList(),
                    pagination
            );
        }

        // 4. Batch fetch default variants for all products
        List<UUID> productIds = products.stream()
                .map(ProductEntity::getProductId)
                .collect(Collectors.toList());

        List<VariantEntity> allVariants = variantRepository
                .findByProductIdInAndEnabledTrue(productIds);

        // Group variants by productId and find default variant for each
        Map<UUID, VariantEntity> variantMap = new HashMap<>();
        Map<UUID, List<VariantEntity>> variantsByProduct = allVariants.stream()
                .collect(Collectors.groupingBy(VariantEntity::getProductId));

        for (Map.Entry<UUID, List<VariantEntity>> entry : variantsByProduct.entrySet()) {
            UUID productId = entry.getKey();
            List<VariantEntity> productVariants = entry.getValue();

            // Pick first variant for each product
            // TODO: When default variant feature is implemented, use that logic instead
            VariantEntity defaultVariant = productVariants.stream()
                    .findFirst()
                    .orElse(null);

            if (defaultVariant != null) {
                variantMap.put(productId, defaultVariant);
            }
        }

        // 5. Batch fetch pricing for all default variants
        List<UUID> variantIds = new ArrayList<>(variantMap.values().stream()
                .map(VariantEntity::getVariantId)
                .collect(Collectors.toList()));

        List<PricingEntity> pricings = pricingRepository
                .findByVariantIdInAndActiveTrue(variantIds);

        Map<UUID, PricingEntity> pricingMap = pricings.stream()
                .collect(Collectors.toMap(PricingEntity::getVariantId, p -> p));

        // 6. Batch fetch inventory for all default variants
        List<InventoryEntity> inventories = inventoryRepository
                .findByVariantIdIn(variantIds);

        Map<UUID, InventoryEntity> inventoryMap = inventories.stream()
                .collect(Collectors.toMap(InventoryEntity::getVariantId, i -> i));

        // 7. Assemble response
        List<com.apna_dukan_backend.catalog.product.model.dto.ProductListItemDto> productItems = assembler.assemble(
                products,
                variantMap,
                pricingMap,
                inventoryMap
        );

        // 8. Create pagination metadata
        // Note: totalElements is based on products that have valid default variants and pricing
        // We need to count all enabled products, but the actual returned count may be less
        // due to filtering (no default variant or no pricing)
        long totalElements = productPage.getTotalElements();
        int totalPages = productPage.getTotalPages();
        boolean hasNext = productPage.hasNext();
        boolean hasPrevious = productPage.hasPrevious();

        ProductListingResponseDto.PaginationMetadata pagination = new ProductListingResponseDto.PaginationMetadata(
                page,
                size,
                totalElements,
                totalPages,
                hasNext,
                hasPrevious
        );

        return new ProductListingResponseDto(
                productGroupId,
                productGroup.getName(),
                productItems,
                pagination
        );
    }
}

