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
import java.util.Comparator;

@Component
public class ProductListingAdminAssembler {

    public List<ProductListItemDto> assemble(
            List<ProductEntity> products,
            Map<UUID, VariantEntity> variantMap,
            Map<UUID, PricingEntity> pricingMap,
            Map<UUID, InventoryEntity> inventoryMap) {

        List<ProductListItemDto> productItems = new ArrayList<>();

        for (ProductEntity product : products) {
            UUID productId = product.getProductId();
            VariantEntity defaultVariant = variantMap.get(productId);

            // For admin, we show products even without variants
            VariantSummaryDto variantSummary = null;
            PricingSummaryDto pricingSummary = null;
            AvailabilityDto availability = null;

            if (defaultVariant != null) {
                UUID variantId = defaultVariant.getVariantId();
                PricingEntity pricing = pricingMap.get(variantId);
                InventoryEntity inventory = inventoryMap.get(variantId);

                // Assemble variant summary
                variantSummary = new VariantSummaryDto(
                        defaultVariant.getVariantId(),
                        defaultVariant.getLabel()
                );

                // Assemble pricing summary if available
                if (pricing != null) {
                    pricingSummary = new PricingSummaryDto(
                            pricing.getSellingPrice(),
                            pricing.getMrp(),
                            pricing.getDiscountPercent(),
                            pricing.getCurrency()
                    );
                }

                // Assemble availability
                boolean inStock = inventory != null && inventory.isInStock();
                availability = new AvailabilityDto(inStock, 0);
            }

            // Assemble image
            ProductImageDto image = new ProductImageDto(
                    product.getPrimaryImageUrl(),
                    product.getImageUrls() != null ? product.getImageUrls() : Collections.emptyList()
            );

            // Assemble product item (always include, even with null variant/pricing)
            ProductListItemDto item = new ProductListItemDto(
                    product.getProductId(),
                    product.getName(),
                    product.getBrand(),
                    image,
                    variantSummary,
                    pricingSummary,
                    availability
            );

            productItems.add(item);
        }

        return productItems;
    }
}

