package com.apna_dukan_backend.inventory.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class InventoryCreateRequestDto {
    @NotBlank(message = "Warehouse ID is required")
    @Schema(description = "Warehouse identifier", example = "WH-001", required = true)
    private String warehouseId;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    @Schema(description = "Initial stock quantity", example = "100", required = true)
    private Integer quantity;

    public InventoryCreateRequestDto() {
    }

    public InventoryCreateRequestDto(String warehouseId, Integer quantity) {
        this.warehouseId = warehouseId;
        this.quantity = quantity;
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
}





