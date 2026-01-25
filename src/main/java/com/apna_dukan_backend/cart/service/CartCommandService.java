package com.apna_dukan_backend.cart.service;

import com.apna_dukan_backend.cart.exception.CartItemNotFoundException;
import com.apna_dukan_backend.cart.model.CartEntity;
import com.apna_dukan_backend.cart.model.CartItemEntity;
import com.apna_dukan_backend.cart.model.domain.CartStatus;
import com.apna_dukan_backend.cart.repository.CartItemRepository;
import com.apna_dukan_backend.cart.repository.CartRepository;
import com.apna_dukan_backend.cart.util.CartValidator;
import com.apna_dukan_backend.catalog.variant.repository.VariantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Service for cart write operations (CQRS Command side).
 * 
 * Responsibilities:
 * - Create/get active cart
 * - Add/update/remove cart items
 * - Validate ownership
 * - Maintain quantity rules
 * - Update cart.updatedAt
 * 
 * Does NOT handle:
 * - Price aggregation
 * - Inventory validation
 */
@Service
@Transactional
public class CartCommandService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final VariantRepository variantRepository;

    public CartCommandService(
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            VariantRepository variantRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.variantRepository = variantRepository;
    }

    /**
     * Get or create an active cart for a user.
     * 
     * @param userId User ID
     * @return Active cart entity
     */
    public CartEntity getOrCreateActiveCart(UUID userId) {
        Optional<CartEntity> existingCart = cartRepository.findActiveCartByUserId(userId);
        
        if (existingCart.isPresent()) {
            return existingCart.get();
        }
        
        // Create new active cart
        CartEntity newCart = new CartEntity();
        newCart.setCartId(UUID.randomUUID());
        newCart.setUserId(userId);
        newCart.setStatus(CartStatus.ACTIVE);
        newCart.setUpdatedAt(LocalDateTime.now());
        
        return cartRepository.save(newCart);
    }

    /**
     * Add an item to cart.
     * 
     * Rules:
     * - If no active cart exists → create one
     * - If item exists → increment quantity
     * - Validate variant exists & is enabled
     * 
     * @param userId User ID
     * @param variantId Variant ID
     * @param quantity Quantity to add
     */
    public void addItem(UUID userId, UUID variantId, Integer quantity) {
        // Validate variant
        CartValidator.validateVariant(variantRepository, variantId);
        CartValidator.validateQuantity(quantity);
        
        // Get or create active cart
        CartEntity cart = getOrCreateActiveCart(userId);
        
        // Check if item already exists
        Optional<CartItemEntity> existingItem = cartItemRepository
                .findByCartIdAndVariantId(cart.getCartId(), variantId);
        
        if (existingItem.isPresent()) {
            // Increment quantity
            CartItemEntity item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            cartItemRepository.save(item);
        } else {
            // Create new cart item
            CartItemEntity newItem = new CartItemEntity();
            newItem.setCartItemId(UUID.randomUUID());
            newItem.setCartId(cart.getCartId());
            newItem.setVariantId(variantId);
            newItem.setQuantity(quantity);
            cartItemRepository.save(newItem);
        }
        
        // Update cart timestamp
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart);
    }

    /**
     * Update cart item quantity.
     * 
     * Rules:
     * - quantity >= 1
     * - If quantity == 0 → remove item
     * 
     * @param userId User ID
     * @param variantId Variant ID
     * @param quantity New quantity
     */
    public void updateItem(UUID userId, UUID variantId, Integer quantity) {
        if (quantity == null || quantity < 0) {
            throw new IllegalArgumentException("Quantity must be 0 or greater");
        }
        
        // Get active cart
        CartEntity cart = cartRepository.findActiveCartByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Active cart not found for user: " + userId));
        
        // Validate ownership
        if (!cart.getUserId().equals(userId)) {
            throw new RuntimeException("Cart does not belong to user: " + userId);
        }
        
        // Find cart item
        CartItemEntity item = cartItemRepository
                .findByCartIdAndVariantId(cart.getCartId(), variantId)
                .orElseThrow(() -> new CartItemNotFoundException(
                        "Cart item not found for variant: " + variantId));
        
        if (quantity == 0) {
            // Remove item
            cartItemRepository.delete(item);
        } else {
            // Update quantity
            CartValidator.validateQuantity(quantity);
            item.setQuantity(quantity);
            cartItemRepository.save(item);
        }
        
        // Update cart timestamp
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart);
    }

    /**
     * Remove a cart item.
     * 
     * Rules:
     * - Remove item if exists
     * - If cart becomes empty → keep cart (do not delete)
     * 
     * @param userId User ID
     * @param variantId Variant ID
     */
    public void removeItem(UUID userId, UUID variantId) {
        // Get active cart
        CartEntity cart = cartRepository.findActiveCartByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Active cart not found for user: " + userId));
        
        // Validate ownership
        if (!cart.getUserId().equals(userId)) {
            throw new RuntimeException("Cart does not belong to user: " + userId);
        }
        
        // Find and delete cart item
        CartItemEntity item = cartItemRepository
                .findByCartIdAndVariantId(cart.getCartId(), variantId)
                .orElseThrow(() -> new CartItemNotFoundException(
                        "Cart item not found for variant: " + variantId));
        
        cartItemRepository.delete(item);
        
        // Update cart timestamp
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart);
    }
}

