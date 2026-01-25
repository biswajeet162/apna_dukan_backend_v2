package com.apna_dukan_backend.cart.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Response DTO for cart summary.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartSummaryDto {
    private Integer totalItems;
    private BigDecimal subtotal;
}

