package com.apna_dukan_backend.inventory.service;

import com.apna_dukan_backend.inventory.exception.DuplicateInventoryException;
import com.apna_dukan_backend.inventory.exception.InvalidInventoryException;
import com.apna_dukan_backend.inventory.exception.InventoryNotFoundException;
import com.apna_dukan_backend.inventory.model.InventoryEntity;
import com.apna_dukan_backend.inventory.model.dto.InventoryAdminResponseDto;
import com.apna_dukan_backend.inventory.model.dto.InventoryCreateRequestDto;
import com.apna_dukan_backend.inventory.model.dto.InventoryUpdateRequestDto;
import com.apna_dukan_backend.inventory.repository.InventoryRepository;
import com.apna_dukan_backend.inventory.service.mapper.InventoryMapper;
import com.apna_dukan_backend.catalog.variant.exception.VariantNotFoundException;
import com.apna_dukan_backend.catalog.variant.model.VariantEntity;
import com.apna_dukan_backend.catalog.variant.repository.VariantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class InventoryCommandService {
    private final InventoryRepository inventoryRepository;
    private final VariantRepository variantRepository;
    private final InventoryMapper inventoryMapper;

    public InventoryCommandService(InventoryRepository inventoryRepository,
                                  VariantRepository variantRepository,
                                  InventoryMapper inventoryMapper) {
        this.inventoryRepository = inventoryRepository;
        this.variantRepository = variantRepository;
        this.inventoryMapper = inventoryMapper;
    }

    public InventoryAdminResponseDto createInventory(UUID variantId, InventoryCreateRequestDto request) {
        // Validate variant exists
        VariantEntity variant = variantRepository.findById(variantId)
                .orElseThrow(() -> new VariantNotFoundException("Variant not found with id: " + variantId));

        // Validate variant is enabled
        if (!variant.isEnabled()) {
            throw new InvalidInventoryException("Cannot create inventory for disabled variant: " + variantId);
        }

        // Validate quantity
        if (request.getQuantity() == null || request.getQuantity() < 0) {
            throw new InvalidInventoryException("Quantity must be greater than or equal to 0");
        }

        // Check if inventory already exists for this variant and warehouse
        Optional<InventoryEntity> existingInventory = inventoryRepository.findByVariantIdAndWarehouseId(
                variantId, request.getWarehouseId());
        if (existingInventory.isPresent()) {
            throw new DuplicateInventoryException(
                    "Inventory already exists for variant " + variantId + " and warehouse " + request.getWarehouseId());
        }

        // Calculate availableQuantity and inStock
        int reservedQuantity = 0; // Default to 0
        int availableQuantity = request.getQuantity() - reservedQuantity;
        boolean inStock = availableQuantity > 0;

        // Create inventory entity
        InventoryEntity entity = new InventoryEntity();
        entity.setInventoryId(UUID.randomUUID());
        entity.setVariantId(variantId);
        entity.setWarehouseId(request.getWarehouseId());
        entity.setQuantity(request.getQuantity());
        entity.setReservedQuantity(reservedQuantity);
        entity.setAvailableQuantity(availableQuantity);
        entity.setInStock(inStock);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setLastUpdatedAt(LocalDateTime.now());

        InventoryEntity saved = inventoryRepository.save(entity);
        return inventoryMapper.toAdminDto(saved);
    }

    public InventoryAdminResponseDto updateInventory(UUID inventoryId, InventoryUpdateRequestDto request) {
        // Validate inventory exists
        InventoryEntity entity = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found with id: " + inventoryId));

        // Update quantity if provided
        if (request.getQuantity() != null) {
            if (request.getQuantity() < 0) {
                throw new InvalidInventoryException("Quantity must be greater than or equal to 0");
            }
            entity.setQuantity(request.getQuantity());
        }

        // Update reservedQuantity if provided
        if (request.getReservedQuantity() != null) {
            if (request.getReservedQuantity() < 0) {
                throw new InvalidInventoryException("Reserved quantity must be greater than or equal to 0");
            }
            entity.setReservedQuantity(request.getReservedQuantity());
        }

        // Validate quantity >= reservedQuantity
        int currentQuantity = entity.getQuantity();
        int currentReservedQuantity = entity.getReservedQuantity();
        if (currentQuantity < currentReservedQuantity) {
            throw new InvalidInventoryException(
                    "Quantity (" + currentQuantity + ") must be greater than or equal to reserved quantity (" 
                    + currentReservedQuantity + ")");
        }

        // Recalculate availableQuantity and inStock
        int availableQuantity = currentQuantity - currentReservedQuantity;
        entity.setAvailableQuantity(availableQuantity);
        entity.setInStock(availableQuantity > 0);
        entity.setLastUpdatedAt(LocalDateTime.now());

        InventoryEntity saved = inventoryRepository.save(entity);
        return inventoryMapper.toAdminDto(saved);
    }
}




