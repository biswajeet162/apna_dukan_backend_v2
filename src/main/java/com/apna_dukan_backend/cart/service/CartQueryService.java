package com.apna_dukan_backend.cart.service;

import com.apna_dukan_backend.cart.model.CartEntity;
import com.apna_dukan_backend.cart.model.CartItemEntity;
import com.apna_dukan_backend.cart.model.domain.CartStatus;
import com.apna_dukan_backend.cart.model.dto.response.CartItemResponseDto;
import com.apna_dukan_backend.cart.model.dto.response.CartResponseDto;
import com.apna_dukan_backend.cart.model.dto.response.CartSummaryDto;
import com.apna_dukan_backend.cart.repository.CartItemRepository;
import com.apna_dukan_backend.cart.repository.CartRepository;
import com.apna_dukan_backend.catalog.pricing.model.PricingEntity;
import com.apna_dukan_backend.catalog.pricing.service.PricingQueryService;
import com.apna_dukan_backend.catalog.product.model.ProductEntity;
import com.apna_dukan_backend.catalog.product.repository.ProductRepository;
import com.apna_dukan_backend.catalog.variant.model.VariantEntity;
import com.apna_dukan_backend.catalog.variant.repository.VariantRepository;
import com.apna_dukan_backend.inventory.model.InventoryEntity;
import com.apna_dukan_backend.inventory.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service for cart read operations and aggregation (CQRS Query side).
 * 
 * Responsibilities:
 * - Fetch active cart
 * - Fetch cart items
 * - Aggregate with Product, Variant, Pricing, Inventory
 * - Calculate subtotal and total items
 * 
 * Aggregation rules:
 * - Price & stock fetched at read time
 * - If variant out of stock → mark unavailable
 * - DO NOT persist aggregated values
 */
@Service
@Transactional(readOnly = true)
public class CartQueryService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final VariantRepository variantRepository;
    private final ProductRepository productRepository;
    private final PricingQueryService pricingQueryService;
    private final InventoryRepository inventoryRepository;

    public CartQueryService(
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            VariantRepository variantRepository,
            ProductRepository productRepository,
            PricingQueryService pricingQueryService,
            InventoryRepository inventoryRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.variantRepository = variantRepository;
        this.productRepository = productRepository;
        this.pricingQueryService = pricingQueryService;
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * Get cart with aggregated data.
     * 
     * Returns empty cart if no active cart exists.
     * 
     * @param userId User ID
     * @return Cart response with aggregated data
     */
    public CartResponseDto getCart(UUID userId) {
        // Get active cart
        Optional<CartEntity> cartOpt = cartRepository.findActiveCartByUserId(userId);
        
        if (cartOpt.isEmpty()) {
            // Return empty cart
            return CartResponseDto.builder()
                    .items(Collections.emptyList())
                    .summary(CartSummaryDto.builder()
                            .totalItems(0)
                            .subtotal(BigDecimal.ZERO)
                            .build())
                    .build();
        }

        CartEntity cart = cartOpt.get();
        
        // Get cart items
        List<CartItemEntity> cartItems = cartItemRepository.findByCartId(cart.getCartId());
        
        if (cartItems.isEmpty()) {
            // Return empty cart
            return CartResponseDto.builder()
                    .items(Collections.emptyList())
                    .summary(CartSummaryDto.builder()
                            .totalItems(0)
                            .subtotal(BigDecimal.ZERO)
                            .build())
                    .build();
        }

        // Extract variant IDs
        List<UUID> variantIds = cartItems.stream()
                .map(CartItemEntity::getVariantId)
                .collect(Collectors.toList());

        // Batch fetch variants
        Map<UUID, VariantEntity> variantMap = variantRepository.findAllById(variantIds).stream()
                .collect(Collectors.toMap(VariantEntity::getVariantId, v -> v));

        // Batch fetch products
        Set<UUID> productIds = variantMap.values().stream()
                .map(VariantEntity::getProductId)
                .collect(Collectors.toSet());
        Map<UUID, ProductEntity> productMap = productRepository.findAllById(productIds).stream()
                .collect(Collectors.toMap(ProductEntity::getProductId, p -> p));

        // Batch fetch pricing
        Map<UUID, PricingEntity> pricingMap = pricingQueryService.getActivePricingByVariantIds(variantIds);

        // Batch fetch inventory
        List<InventoryEntity> inventories = inventoryRepository.findByVariantIdIn(variantIds);
        Map<UUID, Boolean> inStockMap = inventories.stream()
                .collect(Collectors.toMap(
                        InventoryEntity::getVariantId,
                        InventoryEntity::isInStock,
                        (existing, replacement) -> existing || replacement // If any warehouse has stock, variant is in stock
                ));
        // Variants without inventory records are considered out of stock (default false)

        // Build cart items with aggregated data
        List<CartItemResponseDto> items = new ArrayList<>();
        BigDecimal subtotal = BigDecimal.ZERO;
        int totalItems = 0;

        for (CartItemEntity cartItem : cartItems) {
            UUID variantId = cartItem.getVariantId();
            VariantEntity variant = variantMap.get(variantId);
            
            if (variant == null) {
                // Variant not found - skip this item
                continue;
            }

            ProductEntity product = productMap.get(variant.getProductId());
            if (product == null) {
                // Product not found - skip this item
                continue;
            }

            PricingEntity pricing = pricingMap.get(variantId);
            if (pricing == null) {
                // No pricing - skip this item
                continue;
            }

            // Build cart item response
            CartItemResponseDto item = CartItemResponseDto.builder()
                    .variantId(variantId)
                    .productName(product.getName())
                    .variantLabel(variant.getLabel())
                    .quantity(cartItem.getQuantity())
                    .price(CartItemResponseDto.PriceDto.builder()
                            .sellingPrice(pricing.getSellingPrice())
                            .mrp(pricing.getMrp())
                            .discountPercent(pricing.getDiscountPercent())
                            .currency(pricing.getCurrency())
                            .build())
                    .availability(CartItemResponseDto.AvailabilityDto.builder()
                            .inStock(inStockMap.getOrDefault(variantId, false))
                            .build())
                    .build();

            items.add(item);

            // Calculate subtotal
            BigDecimal itemTotal = pricing.getSellingPrice()
                    .multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            subtotal = subtotal.add(itemTotal);
            totalItems += cartItem.getQuantity();
        }

        // Build summary
        CartSummaryDto summary = CartSummaryDto.builder()
                .totalItems(totalItems)
                .subtotal(subtotal)
                .build();

        return CartResponseDto.builder()
                .items(items)
                .summary(summary)
                .build();
    }
}

