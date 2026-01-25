package com.apna_dukan_backend.cart.controller.user;

import com.apna_dukan_backend.cart.model.dto.request.AddCartItemRequestDto;
import com.apna_dukan_backend.cart.model.dto.request.UpdateCartItemRequestDto;
import com.apna_dukan_backend.cart.model.dto.response.CartResponseDto;
import com.apna_dukan_backend.cart.service.CartCommandService;
import com.apna_dukan_backend.cart.service.CartQueryService;
import com.apna_dukan_backend.user.security.UserPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller for user cart management.
 * 
 * All endpoints require authentication with ROLE_USER.
 * userId is derived from JWT SecurityContext, never from request parameters.
 */
@RestController
@RequestMapping("/api/cart")
@Tag(name = "Cart", description = "API for user cart management")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class CartController {
    private final CartCommandService cartCommandService;
    private final CartQueryService cartQueryService;

    /**
     * GET /api/cart
     * 
     * Get the authenticated user's cart with aggregated data.
     * Returns empty cart if no active cart exists.
     */
    @GetMapping
    @Operation(summary = "Get cart", description = "Get the authenticated user's cart with aggregated pricing and availability")
    public ResponseEntity<CartResponseDto> getCart(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        CartResponseDto response = cartQueryService.getCart(userPrincipal.getUserId());
        return ResponseEntity.ok(response);
    }

    /**
     * POST /api/cart/items
     * 
     * Add an item to cart.
     * 
     * Rules:
     * - If no active cart exists → create one
     * - If item exists → increment quantity
     * - Validate variant exists & is enabled
     */
    @PostMapping("/items")
    @Operation(summary = "Add item to cart", description = "Add an item to the authenticated user's cart")
    public ResponseEntity<Void> addItem(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @Valid @RequestBody AddCartItemRequestDto request) {
        cartCommandService.addItem(
                userPrincipal.getUserId(),
                request.getVariantId(),
                request.getQuantity());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * PUT /api/cart/items/{variantId}
     * 
     * Update cart item quantity.
     * 
     * Rules:
     * - quantity >= 1
     * - If quantity == 0 → remove item
     */
    @PutMapping("/items/{variantId}")
    @Operation(summary = "Update cart item", description = "Update the quantity of an item in the authenticated user's cart")
    public ResponseEntity<Void> updateItem(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable UUID variantId,
            @Valid @RequestBody UpdateCartItemRequestDto request) {
        cartCommandService.updateItem(
                userPrincipal.getUserId(),
                variantId,
                request.getQuantity());
        return ResponseEntity.ok().build();
    }

    /**
     * DELETE /api/cart/items/{variantId}
     * 
     * Remove a cart item.
     * 
     * Rules:
     * - Remove item if exists
     * - If cart becomes empty → keep cart (do not delete)
     */
    @DeleteMapping("/items/{variantId}")
    @Operation(summary = "Remove cart item", description = "Remove an item from the authenticated user's cart")
    public ResponseEntity<Void> removeItem(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable UUID variantId) {
        cartCommandService.removeItem(userPrincipal.getUserId(), variantId);
        return ResponseEntity.noContent().build();
    }
}

