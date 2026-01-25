package com.apna_dukan_backend.cart.util;

import com.apna_dukan_backend.catalog.variant.exception.VariantNotFoundException;
import com.apna_dukan_backend.catalog.variant.model.VariantEntity;
import com.apna_dukan_backend.catalog.variant.repository.VariantRepository;

import java.util.UUID;

/**
 * Utility class for cart validation.
 * 
 * Validates:
 * - Variant exists
 * - Variant is enabled
 * 
 * Does NOT validate:
 * - Inventory/stock (handled at checkout)
 * - Price (aggregated at read time)
 */
public class CartValidator {
    
    /**
     * Validates that a variant exists and is enabled.
     * 
     * @param variantRepository Variant repository
     * @param variantId Variant ID to validate
     * @throws VariantNotFoundException if variant doesn't exist or is disabled
     */
    public static void validateVariant(VariantRepository variantRepository, UUID variantId) {
        VariantEntity variant = variantRepository.findById(variantId)
                .orElseThrow(() -> new VariantNotFoundException("Variant not found with id: " + variantId));
        
        if (!variant.isEnabled()) {
            throw new VariantNotFoundException("Variant is disabled: " + variantId);
        }
    }
    
    /**
     * Validates quantity is positive.
     * 
     * @param quantity Quantity to validate
     * @throws IllegalArgumentException if quantity is invalid
     */
    public static void validateQuantity(Integer quantity) {
        if (quantity == null || quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }
    }
}

