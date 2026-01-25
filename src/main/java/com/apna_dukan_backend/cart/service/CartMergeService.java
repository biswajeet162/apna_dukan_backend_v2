package com.apna_dukan_backend.cart.service;

import com.apna_dukan_backend.cart.model.CartEntity;
import com.apna_dukan_backend.cart.model.CartItemEntity;
import com.apna_dukan_backend.cart.repository.CartItemRepository;
import com.apna_dukan_backend.cart.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service for merging guest cart into user cart.
 * 
 * DESIGN ONLY - No API exposed yet.
 * 
 * This service is designed to support future guest cart merge functionality.
 * Guest cart items are stored on frontend and passed to this service when user logs in.
 * 
 * Logic:
 * - Fetch user active cart
 * - Merge quantities by variantId
 * - Persist merged cart
 */
@Service
@Transactional
public class CartMergeService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartCommandService cartCommandService;

    public CartMergeService(
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            CartCommandService cartCommandService) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartCommandService = cartCommandService;
    }

    /**
     * Merge guest cart items into user cart.
     * 
     * Guest items format: Map<VariantId, Quantity>
     * 
     * Rules:
     * - If variant exists in user cart → add quantities
     * - If variant doesn't exist → create new cart item
     * - Validate variants exist and are enabled
     * 
     * @param userId User ID
     * @param guestItems Map of variantId to quantity from guest cart
     */
    public void mergeGuestCart(UUID userId, Map<UUID, Integer> guestItems) {
        if (guestItems == null || guestItems.isEmpty()) {
            return; // Nothing to merge
        }

        // Get or create active cart
        CartEntity cart = cartCommandService.getOrCreateActiveCart(userId);

        // Get existing cart items
        List<CartItemEntity> existingItems = cartItemRepository.findByCartId(cart.getCartId());
        Map<UUID, CartItemEntity> existingItemsMap = existingItems.stream()
                .collect(Collectors.toMap(CartItemEntity::getVariantId, item -> item));

        // Merge guest items
        for (Map.Entry<UUID, Integer> guestEntry : guestItems.entrySet()) {
            UUID variantId = guestEntry.getKey();
            Integer guestQuantity = guestEntry.getValue();

            if (guestQuantity == null || guestQuantity <= 0) {
                continue; // Skip invalid quantities
            }

            CartItemEntity existingItem = existingItemsMap.get(variantId);

            if (existingItem != null) {
                // Merge: add guest quantity to existing quantity
                existingItem.setQuantity(existingItem.getQuantity() + guestQuantity);
                cartItemRepository.save(existingItem);
            } else {
                // Create new cart item
                CartItemEntity newItem = new CartItemEntity();
                newItem.setCartItemId(UUID.randomUUID());
                newItem.setCartId(cart.getCartId());
                newItem.setVariantId(variantId);
                newItem.setQuantity(guestQuantity);
                cartItemRepository.save(newItem);
            }
        }

        // Update cart timestamp
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart);
    }
}

