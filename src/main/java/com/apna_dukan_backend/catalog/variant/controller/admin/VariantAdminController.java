package com.apna_dukan_backend.catalog.variant.controller.admin;

import com.apna_dukan_backend.catalog.variant.model.dto.EnableDisableVariantRequest;
import com.apna_dukan_backend.catalog.variant.model.dto.VariantAdminResponseDto;
import com.apna_dukan_backend.catalog.variant.model.dto.VariantCreateRequestDto;
import com.apna_dukan_backend.catalog.variant.model.dto.VariantUpdateRequestDto;
import com.apna_dukan_backend.catalog.variant.service.VariantCommandService;
import com.apna_dukan_backend.catalog.variant.service.VariantQueryService;
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
@Tag(name = "Variant Admin", description = "Admin API for managing product variants")
public class VariantAdminController {
    private final VariantCommandService variantCommandService;
    private final VariantQueryService variantQueryService;

    public VariantAdminController(VariantCommandService variantCommandService,
                                  VariantQueryService variantQueryService) {
        this.variantCommandService = variantCommandService;
        this.variantQueryService = variantQueryService;
    }

    @GetMapping("/variants")
    @Operation(summary = "Get all variants",
               description = "Returns a list of all variants (enabled and disabled) in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all variants",
                    content = @Content(schema = @Schema(implementation = VariantAdminResponseDto.class)))
    })
    public ResponseEntity<List<VariantAdminResponseDto>> getAllVariants() {
        List<VariantAdminResponseDto> variants = variantQueryService.getAllVariants();
        return ResponseEntity.ok(variants);
    }

    @GetMapping("/variants/{variantId}")
    @Operation(summary = "Get variant by ID",
               description = "Returns a single variant by its ID with all details including timestamps")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved variant",
                    content = @Content(schema = @Schema(implementation = VariantAdminResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Variant not found")
    })
    public ResponseEntity<VariantAdminResponseDto> getVariantById(@PathVariable UUID variantId) {
        VariantAdminResponseDto variant = variantQueryService.getVariantById(variantId);
        return ResponseEntity.ok(variant);
    }

    @GetMapping("/products/{productId}/variants")
    @Operation(summary = "Get all variants for a product",
               description = "Returns a list of all variants (enabled and disabled) for a specific product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved variants",
                    content = @Content(schema = @Schema(implementation = VariantAdminResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<List<VariantAdminResponseDto>> getVariantsByProductId(@PathVariable UUID productId) {
        List<VariantAdminResponseDto> variants = variantQueryService.getVariantsByProductId(productId);
        return ResponseEntity.ok(variants);
    }

    @PostMapping("/products/{productId}/variants")
    @Operation(summary = "Create a new variant",
               description = "Creates a new variant for a product. Variant will be disabled by default and is not sellable until pricing and inventory are added.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Variant created successfully",
                    content = @Content(schema = @Schema(implementation = VariantAdminResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data or Product not found"),
            @ApiResponse(responseCode = "409", description = "SKU already exists")
    })
    public ResponseEntity<VariantAdminResponseDto> createVariant(
            @PathVariable UUID productId,
            @Valid @RequestBody VariantCreateRequestDto request) {
        VariantAdminResponseDto created = variantCommandService.createVariant(productId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/variants/{variantId}")
    @Operation(summary = "Update a variant",
               description = "Updates an existing variant. Only provided fields will be updated. SKU and productId cannot be changed.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Variant updated successfully",
                    content = @Content(schema = @Schema(implementation = VariantAdminResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Variant not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    public ResponseEntity<VariantAdminResponseDto> updateVariant(
            @PathVariable UUID variantId,
            @Valid @RequestBody VariantUpdateRequestDto request) {
        VariantAdminResponseDto updated = variantCommandService.updateVariant(variantId, request);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/variants/{variantId}/enable")
    @Operation(summary = "Enable or disable a variant",
               description = "Enables or disables a variant. When disabled, variant will disappear from user APIs.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Variant status updated successfully",
                    content = @Content(schema = @Schema(implementation = VariantAdminResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Variant not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    public ResponseEntity<VariantAdminResponseDto> updateVariantEnabledStatus(
            @PathVariable UUID variantId,
            @Valid @RequestBody EnableDisableVariantRequest request) {
        VariantAdminResponseDto updated = variantCommandService.updateVariantEnabledStatus(variantId, request.getEnabled());
        return ResponseEntity.ok(updated);
    }
}

