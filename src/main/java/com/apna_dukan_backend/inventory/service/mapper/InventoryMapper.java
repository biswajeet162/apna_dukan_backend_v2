package com.apna_dukan_backend.inventory.service.mapper;

import com.apna_dukan_backend.inventory.model.InventoryEntity;
import com.apna_dukan_backend.inventory.model.domain.Inventory;
import com.apna_dukan_backend.inventory.model.dto.InventoryAdminResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InventoryMapper {

    public Inventory toDomain(InventoryEntity entity) {
        if (entity == null) {
            return null;
        }
        Inventory inventory = new Inventory();
        inventory.setInventoryId(entity.getInventoryId());
        inventory.setVariantId(entity.getVariantId());
        inventory.setWarehouseId(entity.getWarehouseId());
        inventory.setQuantity(entity.getQuantity());
        inventory.setReservedQuantity(entity.getReservedQuantity());
        inventory.setAvailableQuantity(entity.getAvailableQuantity());
        inventory.setInStock(entity.isInStock());
        inventory.setLastUpdatedAt(entity.getLastUpdatedAt());
        return inventory;
    }

    public InventoryEntity toEntity(Inventory inventory) {
        if (inventory == null) {
            return null;
        }
        InventoryEntity entity = new InventoryEntity();
        entity.setInventoryId(inventory.getInventoryId());
        entity.setVariantId(inventory.getVariantId());
        entity.setWarehouseId(inventory.getWarehouseId());
        entity.setQuantity(inventory.getQuantity());
        entity.setReservedQuantity(inventory.getReservedQuantity());
        entity.setAvailableQuantity(inventory.getAvailableQuantity());
        entity.setInStock(inventory.isInStock());
        entity.setLastUpdatedAt(inventory.getLastUpdatedAt());
        return entity;
    }

    public InventoryAdminResponseDto toAdminDto(InventoryEntity entity) {
        if (entity == null) {
            return null;
        }
        return new InventoryAdminResponseDto(
                entity.getInventoryId(),
                entity.getVariantId(),
                entity.getWarehouseId(),
                entity.getQuantity(),
                entity.getReservedQuantity(),
                entity.getAvailableQuantity(),
                entity.isInStock(),
                entity.getLastUpdatedAt()
        );
    }

    public List<InventoryAdminResponseDto> toAdminDtoList(List<InventoryEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toAdminDto)
                .collect(Collectors.toList());
    }
}


