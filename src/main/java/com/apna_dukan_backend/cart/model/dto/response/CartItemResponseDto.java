package com.apna_dukan_backend.cart.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Response DTO for a cart item with aggregated data.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemResponseDto {
    private UUID variantId;
    private String productName;
    private String variantLabel;
    private Integer quantity;
    
    private PriceDto price;
    private AvailabilityDto availability;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PriceDto {
        private BigDecimal sellingPrice;
        private BigDecimal mrp;
        private Integer discountPercent;
        private String currency;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AvailabilityDto {
        private Boolean inStock;
    }
}

