package com.apna_dukan_backend.catalog.pricing.controller.admin;

import com.apna_dukan_backend.catalog.pricing.model.dto.PricingAdminResponseDto;
import com.apna_dukan_backend.catalog.pricing.model.dto.PricingCreateRequestDto;
import com.apna_dukan_backend.catalog.pricing.model.dto.PricingUpdateRequestDto;
import com.apna_dukan_backend.catalog.pricing.service.PricingCommandService;
import com.apna_dukan_backend.catalog.pricing.service.PricingQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "Pricing Admin", description = "Admin API for managing variant pricing")
public class PricingAdminController {
    private final PricingCommandService pricingCommandService;
    private final PricingQueryService pricingQueryService;

    public PricingAdminController(PricingCommandService pricingCommandService,
                                  PricingQueryService pricingQueryService) {
        this.pricingCommandService = pricingCommandService;
        this.pricingQueryService = pricingQueryService;
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

    @GetMapping("/pricing")
    @Operation(summary = "Get all pricing records",
               description = "Returns a list of all pricing records (active and inactive) in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all pricing records",
                    content = @Content(schema = @Schema(implementation = PricingAdminResponseDto.class)))
    })
    public ResponseEntity<List<PricingAdminResponseDto>> getAllPricing() {
        List<PricingAdminResponseDto> pricingList = pricingQueryService.getAllPricing();
        return ResponseEntity.ok(pricingList);
    }

    @GetMapping("/variants/{variantId}/pricing")
    @Operation(summary = "Get all pricing records for a variant",
               description = "Returns a list of all pricing records (active and inactive) for a specific variant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved pricing records",
                    content = @Content(schema = @Schema(implementation = PricingAdminResponseDto.class)))
    })
    public ResponseEntity<List<PricingAdminResponseDto>> getPricingByVariantId(
            @PathVariable UUID variantId) {
        List<PricingAdminResponseDto> pricingList = pricingQueryService.getPricingByVariantId(variantId);
        return ResponseEntity.ok(pricingList);
    }

    @GetMapping("/products/{productId}/pricing")
    @Operation(summary = "Get all pricing records for a product",
               description = "Returns a list of all pricing records (active and inactive) for all variants of a specific product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved pricing records",
                    content = @Content(schema = @Schema(implementation = PricingAdminResponseDto.class)))
    })
    public ResponseEntity<List<PricingAdminResponseDto>> getPricingByProductId(
            @PathVariable UUID productId) {
        List<PricingAdminResponseDto> pricingList = pricingQueryService.getPricingByProductId(productId);
        return ResponseEntity.ok(pricingList);
    }

    @GetMapping("/product-groups/{productGroupId}/pricing")
    @Operation(summary = "Get all pricing records for a product group",
               description = "Returns a list of all pricing records (active and inactive) for all variants of all products in a specific product group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved pricing records",
                    content = @Content(schema = @Schema(implementation = PricingAdminResponseDto.class)))
    })
    public ResponseEntity<List<PricingAdminResponseDto>> getPricingByProductGroupId(
            @PathVariable UUID productGroupId) {
        List<PricingAdminResponseDto> pricingList = pricingQueryService.getPricingByProductGroupId(productGroupId);
        return ResponseEntity.ok(pricingList);
    }
}

