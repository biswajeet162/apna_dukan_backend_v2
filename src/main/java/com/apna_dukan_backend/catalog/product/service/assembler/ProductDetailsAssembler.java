package com.apna_dukan_backend.catalog.product.service.assembler;

import com.apna_dukan_backend.catalog.product.model.ProductEntity;
import com.apna_dukan_backend.catalog.product.model.dto.*;
import com.apna_dukan_backend.catalog.variant.model.VariantEntity;
import com.apna_dukan_backend.inventory.model.InventoryEntity;
import com.apna_dukan_backend.catalog.pricing.model.PricingEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class ProductDetailsAssembler {

    public ProductDetailsDto assemble(
            ProductEntity product,
            List<VariantEntity> variants,
            Map<java.util.UUID, PricingEntity> pricingMap,
            Map<java.util.UUID, InventoryEntity> inventoryMap) {

        // Assemble images
        ProductImageDto images = assembleImages(product);

        // Assemble variants
        List<VariantDetailsDto> variantDetails = assembleVariants(
                variants,
                pricingMap,
                inventoryMap
        );

        // Assemble product details
        return new ProductDetailsDto(
                product.getProductId(),
                product.getName(),
                product.getBrand(),
                null, // ProductEntity doesn't have description field
                images,
                variantDetails
        );
    }

    private ProductImageDto assembleImages(ProductEntity product) {
        String primary = product.getPrimaryImageUrl();
        List<String> gallery = Collections.emptyList(); // ProductEntity doesn't have gallery field
        
        return new ProductImageDto(primary, gallery);
    }

    private List<VariantDetailsDto> assembleVariants(
            List<VariantEntity> variants,
            Map<java.util.UUID, PricingEntity> pricingMap,
            Map<java.util.UUID, InventoryEntity> inventoryMap) {

        if (variants == null || variants.isEmpty()) {
            return Collections.emptyList();
        }

        List<VariantDetailsDto> variantDetails = new ArrayList<>();

        for (VariantEntity variant : variants) {
            java.util.UUID variantId = variant.getVariantId();
            
            // Skip variant if no active pricing
            PricingEntity pricing = pricingMap.get(variantId);
            if (pricing == null) {
                continue;
            }

            // Assemble pricing
            PricingDetailsDto pricingDto = assemblePricing(pricing);

            // Assemble availability
            AvailabilityDto availability = assembleAvailability(
                    inventoryMap.get(variantId)
            );

            // Assemble variant
            VariantDetailsDto variantDto = new VariantDetailsDto(
                    variantId,
                    variant.getLabel(),
                    Collections.emptyMap(), // VariantEntity doesn't have attributes field
                    pricingDto,
                    availability
            );

            variantDetails.add(variantDto);
        }

        return variantDetails;
    }

    private PricingDetailsDto assemblePricing(PricingEntity pricing) {
        return new PricingDetailsDto(
                pricing.getSellingPrice().doubleValue(),
                pricing.getMrp().doubleValue(),
                pricing.getDiscountPercent(),
                pricing.getCurrency()
        );
    }

    private AvailabilityDto assembleAvailability(InventoryEntity inventory) {
        if (inventory == null) {
            // If inventory missing: inStock = false, availableQuantity = 0
            return new AvailabilityDto(false, 0);
        }

        // InventoryEntity doesn't have availableQuantity field
        // Return inStock value and 0 for availableQuantity
        return new AvailabilityDto(inventory.isInStock(), 0);
    }
}

