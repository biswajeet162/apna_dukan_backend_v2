package com.apna_dukan_backend.user.controller;

import com.apna_dukan_backend.user.dto.address.AddressRequest;
import com.apna_dukan_backend.user.dto.address.AddressResponse;
import com.apna_dukan_backend.user.security.UserPrincipal;
import com.apna_dukan_backend.user.service.AddressService;
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

@RestController
@RequestMapping("/api/user/addresses")
@Tag(name = "User Addresses", description = "API for user address management")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserAddressController {
    private final AddressService addressService;

    @GetMapping
    @Operation(summary = "Get user addresses", description = "Get all addresses for the authenticated user")
    public ResponseEntity<List<AddressResponse>> getAddresses(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<AddressResponse> addresses = addressService.getAddresses(userPrincipal.getUserId());
        return ResponseEntity.ok(addresses);
    }

    @PostMapping
    @Operation(summary = "Add address", description = "Add a new address for the authenticated user")
    public ResponseEntity<AddressResponse> addAddress(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @Valid @RequestBody AddressRequest request) {
        AddressResponse address = addressService.addAddress(userPrincipal.getUserId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(address);
    }

    @PutMapping("/{addressId}")
    @Operation(summary = "Update address", description = "Update an existing address for the authenticated user")
    public ResponseEntity<AddressResponse> updateAddress(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable UUID addressId,
            @Valid @RequestBody AddressRequest request) {
        AddressResponse address = addressService.updateAddress(
                userPrincipal.getUserId(), addressId, request);
        return ResponseEntity.ok(address);
    }

    @DeleteMapping("/{addressId}")
    @Operation(summary = "Delete address", description = "Delete an address for the authenticated user")
    public ResponseEntity<Void> deleteAddress(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable UUID addressId) {
        addressService.deleteAddress(userPrincipal.getUserId(), addressId);
        return ResponseEntity.noContent().build();
    }
}

