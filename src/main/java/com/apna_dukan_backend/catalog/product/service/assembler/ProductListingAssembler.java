package com.apna_dukan_backend.catalog.product.service.assembler;

import com.apna_dukan_backend.catalog.product.model.ProductEntity;
import com.apna_dukan_backend.catalog.product.model.dto.*;
import com.apna_dukan_backend.catalog.variant.model.VariantEntity;
import com.apna_dukan_backend.inventory.model.InventoryEntity;
import com.apna_dukan_backend.pricing.model.PricingEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProductListingAssembler {

    public List<ProductListItemDto> assemble(
            List<ProductEntity> products,
            Map<UUID, VariantEntity> variantMap,
            Map<UUID, PricingEntity> pricingMap,
            Map<UUID, InventoryEntity> inventoryMap) {

        List<ProductListItemDto> productItems = new ArrayList<>();

        for (ProductEntity product : products) {
            UUID productId = product.getProductId();
            VariantEntity defaultVariant = variantMap.get(productId);

            // Skip product if no enabled default variant
            if (defaultVariant == null) {
                continue;
            }

            UUID variantId = defaultVariant.getVariantId();
            PricingEntity pricing = pricingMap.get(variantId);

            // Skip product if no active pricing
            if (pricing == null) {
                continue;
            }

            InventoryEntity inventory = inventoryMap.get(variantId);
            boolean inStock = inventory != null && inventory.isInStock();

            ProductListItemDto item = assembleProductItem(
                    product,
                    defaultVariant,
                    pricing,
                    inStock
            );

            productItems.add(item);
        }

        return productItems;
    }

    private ProductListItemDto assembleProductItem(
            ProductEntity product,
            VariantEntity variant,
            PricingEntity pricing,
            boolean inStock) {

        // Assemble image
        ProductImageDto image = new ProductImageDto(
                product.getPrimaryImageUrl(),
                Collections.emptyList() // ProductEntity doesn't have gallery field
        );

        // Assemble variant summary
        VariantSummaryDto variantSummary = new VariantSummaryDto(
                variant.getVariantId(),
                variant.getLabel()
        );

        // Assemble pricing summary
        PricingSummaryDto pricingSummary = new PricingSummaryDto(
                pricing.getSellingPrice(),
                pricing.getMrp(),
                pricing.getDiscountPercent(),
                pricing.getCurrency()
        );

        // Assemble availability
        AvailabilityDto availability = new AvailabilityDto(inStock, 0); // InventoryEntity doesn't have availableQuantity field

        // Assemble product item
        return new ProductListItemDto(
                product.getProductId(),
                product.getName(),
                product.getBrand(),
                image,
                variantSummary,
                pricingSummary,
                availability
        );
    }
}

