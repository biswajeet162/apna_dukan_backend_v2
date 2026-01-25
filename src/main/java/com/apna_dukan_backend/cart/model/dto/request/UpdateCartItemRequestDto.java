package com.apna_dukan_backend.cart.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for updating a cart item quantity.
 * 
 * Rules:
 * - quantity >= 1
 * - If quantity == 0, item is removed (handled in service)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCartItemRequestDto {
    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity must be 0 or greater")
    private Integer quantity;
}

