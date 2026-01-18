package com.apna_dukan_backend.catalog.pricing.controller.admin;

import com.apna_dukan_backend.catalog.pricing.model.dto.PricingAdminResponseDto;
import com.apna_dukan_backend.catalog.pricing.model.dto.PricingCreateRequestDto;
import com.apna_dukan_backend.catalog.pricing.model.dto.PricingUpdateRequestDto;
import com.apna_dukan_backend.catalog.pricing.service.PricingCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "Pricing Admin", description = "Admin API for managing variant pricing")
public class PricingAdminController {
    private final PricingCommandService pricingCommandService;

    public PricingAdminController(PricingCommandService pricingCommandService) {
        this.pricingCommandService = pricingCommandService;
    }

    @PostMapping("/variants/{variantId}/pricing")
    @Operation(summary = "Create pricing for a variant",
               description = "Creates a new pricing for a variant. If an active pricing already exists, it will be deactivated. Only one active pricing per variant is allowed.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pricing created successfully",
                    content = @Content(schema = @Schema(implementation = PricingAdminResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data, variant not found, or variant is disabled"),
            @ApiResponse(responseCode = "409", description = "Duplicate active pricing conflict")
    })
    public ResponseEntity<PricingAdminResponseDto> createPricing(
            @PathVariable UUID variantId,
            @Valid @RequestBody PricingCreateRequestDto request) {
        PricingAdminResponseDto created = pricingCommandService.createPricing(variantId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/pricing/{pricingId}")
    @Operation(summary = "Update pricing",
               description = "Updates an existing pricing. Only provided fields will be updated. Variant ID and currency cannot be changed. If activating pricing, ensures no other active pricing exists for the variant.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pricing updated successfully",
                    content = @Content(schema = @Schema(implementation = PricingAdminResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data or validation failed"),
            @ApiResponse(responseCode = "404", description = "Pricing not found"),
            @ApiResponse(responseCode = "409", description = "Duplicate active pricing conflict")
    })
    public ResponseEntity<PricingAdminResponseDto> updatePricing(
            @PathVariable UUID pricingId,
            @Valid @RequestBody PricingUpdateRequestDto request) {
        PricingAdminResponseDto updated = pricingCommandService.updatePricing(pricingId, request);
        return ResponseEntity.ok(updated);
    }
}

