package com.apna_dukan_backend.inventory.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;

public class InventoryUpdateRequestDto {
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    @Schema(description = "Updated stock quantity", example = "150")
    private Integer quantity;

    @Min(value = 0, message = "Reserved quantity must be greater than or equal to 0")
    @Schema(description = "Updated reserved quantity", example = "10")
    private Integer reservedQuantity;

    public InventoryUpdateRequestDto() {
    }

    public InventoryUpdateRequestDto(Integer quantity, Integer reservedQuantity) {
        this.quantity = quantity;
        this.reservedQuantity = reservedQuantity;
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
}





