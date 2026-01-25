package com.apna_dukan_backend.cart.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Response DTO for cart with aggregated data.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponseDto {
    private List<CartItemResponseDto> items;
    private CartSummaryDto summary;
}

