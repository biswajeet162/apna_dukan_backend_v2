package com.apna_dukan_backend.user.controller.user;

import com.apna_dukan_backend.user.model.dto.address.AddressCreateRequestDto;
import com.apna_dukan_backend.user.model.dto.address.AddressResponseDto;
import com.apna_dukan_backend.user.model.dto.address.AddressUpdateRequestDto;
import com.apna_dukan_backend.user.security.UserPrincipal;
import com.apna_dukan_backend.user.service.UserAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller for user address management.
 * 
 * All endpoints require authentication with ROLE_USER.
 * userId is derived from JWT SecurityContext, never from request parameters.
 */
@RestController
@RequestMapping("/api/user/addresses")
@Tag(name = "User Addresses", description = "API for user address management")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserAddressController {
    private final UserAddressService userAddressService;

    /**
     * GET /api/user/addresses
     * 
     * Get all addresses for the authenticated user.
     * Default address is returned first.
     */
    @GetMapping
    @Operation(summary = "Get user addresses", description = "Get all addresses for the authenticated user")
    public ResponseEntity<List<AddressResponseDto>> getAddresses(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<AddressResponseDto> addresses = userAddressService.listAddresses(userPrincipal.getUserId());
        return ResponseEntity.ok(addresses);
    }

    /**
     * POST /api/user/addresses
     * 
     * Create a new address for the authenticated user.
     * 
     * Rules:
     * - If isDefault = true, unset previous default address
     * - Pincode format is validated (6 digits)
     */
    @PostMapping
    @Operation(summary = "Create address", description = "Create a new address for the authenticated user")
    public ResponseEntity<AddressResponseDto> createAddress(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @Valid @RequestBody AddressCreateRequestDto request) {
        AddressResponseDto address = userAddressService.createAddress(
                userPrincipal.getUserId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(address);
    }

    /**
     * PUT /api/user/addresses/{addressId}
     * 
     * Update an existing address for the authenticated user.
     * 
     * Rules:
     * - Address must belong to the authenticated user
     * - If isDefault = true, unset previous default address
     */
    @PutMapping("/{addressId}")
    @Operation(summary = "Update address", description = "Update an existing address for the authenticated user")
    public ResponseEntity<AddressResponseDto> updateAddress(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable UUID addressId,
            @Valid @RequestBody AddressUpdateRequestDto request) {
        AddressResponseDto address = userAddressService.updateAddress(
                userPrincipal.getUserId(), addressId, request);
        return ResponseEntity.ok(address);
    }

    /**
     * DELETE /api/user/addresses/{addressId}
     * 
     * Delete an address for the authenticated user.
     * 
     * Rules:
     * - Address must belong to the authenticated user
     * - If deleted address was default, auto-select another address as default (if exists)
     */
    @DeleteMapping("/{addressId}")
    @Operation(summary = "Delete address", description = "Delete an address for the authenticated user")
    public ResponseEntity<Void> deleteAddress(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable UUID addressId) {
        userAddressService.deleteAddress(userPrincipal.getUserId(), addressId);
        return ResponseEntity.noContent().build();
    }
}

