package com.apna_dukan_backend.inventory.model.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Inventory {
    private UUID inventoryId;
    private UUID variantId;
    private String warehouseId;
    private Integer quantity;
    private Integer reservedQuantity;
    private Integer availableQuantity;
    private boolean inStock;
    private LocalDateTime lastUpdatedAt;

    public Inventory() {
    }

    public Inventory(UUID inventoryId, UUID variantId, String warehouseId, Integer quantity,
                     Integer reservedQuantity, Integer availableQuantity, boolean inStock,
                     LocalDateTime lastUpdatedAt) {
        this.inventoryId = inventoryId;
        this.variantId = variantId;
        this.warehouseId = warehouseId;
        this.quantity = quantity;
        this.reservedQuantity = reservedQuantity;
        this.availableQuantity = availableQuantity;
        this.inStock = inStock;
        this.lastUpdatedAt = lastUpdatedAt;
    }

    // Getters and Setters
    public UUID getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(UUID inventoryId) {
        this.inventoryId = inventoryId;
    }

    public UUID getVariantId() {
        return variantId;
    }

    public void setVariantId(UUID variantId) {
        this.variantId = variantId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getReservedQuantity() {
        return reservedQuantity;
    }

    public void setReservedQuantity(Integer reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }
}




