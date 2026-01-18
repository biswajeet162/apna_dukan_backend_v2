package com.apna_dukan_backend.inventory.service;

import com.apna_dukan_backend.catalog.product.exception.ProductNotFoundException;
import com.apna_dukan_backend.catalog.product.repository.ProductRepository;
import com.apna_dukan_backend.catalog.variant.exception.VariantNotFoundException;
import com.apna_dukan_backend.catalog.variant.repository.VariantRepository;
import com.apna_dukan_backend.inventory.exception.InventoryNotFoundException;
import com.apna_dukan_backend.inventory.model.InventoryEntity;
import com.apna_dukan_backend.inventory.model.dto.InventoryAdminResponseDto;
import com.apna_dukan_backend.inventory.repository.InventoryRepository;
import com.apna_dukan_backend.inventory.service.mapper.InventoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class InventoryQueryService {
    private final InventoryRepository inventoryRepository;
    private final VariantRepository variantRepository;
    private final ProductRepository productRepository;
    private final InventoryMapper inventoryMapper;

    public InventoryQueryService(InventoryRepository inventoryRepository,
                                VariantRepository variantRepository,
                                ProductRepository productRepository,
                                InventoryMapper inventoryMapper) {
        this.inventoryRepository = inventoryRepository;
        this.variantRepository = variantRepository;
        this.productRepository = productRepository;
        this.inventoryMapper = inventoryMapper;
    }

    /**
     * Get all inventory records
     * @return List of all inventory records
     */
    public List<InventoryAdminResponseDto> getAllInventories() {
        List<InventoryEntity> entities = inventoryRepository.findAll();
        return inventoryMapper.toAdminDtoList(entities);
    }

    /**
     * Get inventory by inventory ID
     * @param inventoryId Inventory ID
     * @return Inventory record
     */
    public InventoryAdminResponseDto getInventoryById(UUID inventoryId) {
        InventoryEntity entity = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found with id: " + inventoryId));
        return inventoryMapper.toAdminDto(entity);
    }

    /**
     * Get all inventory records for a variant
     * @param variantId Variant ID
     * @return List of inventory records for the variant (can have multiple warehouses)
     */
    public List<InventoryAdminResponseDto> getInventoriesByVariantId(UUID variantId) {
        // Validate variant exists
        variantRepository.findById(variantId)
                .orElseThrow(() -> new VariantNotFoundException("Variant not found with id: " + variantId));

        List<InventoryEntity> entities = inventoryRepository.findByVariantId(variantId);
        return inventoryMapper.toAdminDtoList(entities);
    }

    /**
     * Get all inventory records for a product
     * @param productId Product ID
     * @return List of inventory records for all variants of the product
     */
    public List<InventoryAdminResponseDto> getInventoriesByProductId(UUID productId) {
        // Validate product exists
        productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        // Get all variants for the product
        List<UUID> variantIds = variantRepository.findByProductId(productId).stream()
                .map(variant -> variant.getVariantId())
                .toList();

        if (variantIds.isEmpty()) {
            return List.of();
        }

        // Get all inventories for these variants
        List<InventoryEntity> entities = inventoryRepository.findByVariantIdIn(variantIds);
        return inventoryMapper.toAdminDtoList(entities);
    }
}

