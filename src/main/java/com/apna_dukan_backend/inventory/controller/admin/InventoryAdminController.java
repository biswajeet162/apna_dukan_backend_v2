package com.apna_dukan_backend.inventory.controller.admin;

import com.apna_dukan_backend.inventory.model.dto.InventoryAdminResponseDto;
import com.apna_dukan_backend.inventory.model.dto.InventoryCreateRequestDto;
import com.apna_dukan_backend.inventory.model.dto.InventoryUpdateRequestDto;
import com.apna_dukan_backend.inventory.service.InventoryCommandService;
import com.apna_dukan_backend.inventory.service.InventoryQueryService;
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
@Tag(name = "Inventory Admin", description = "Admin API for managing inventory")
public class InventoryAdminController {
    private final InventoryCommandService inventoryCommandService;
    private final InventoryQueryService inventoryQueryService;

    public InventoryAdminController(InventoryCommandService inventoryCommandService,
                                   InventoryQueryService inventoryQueryService) {
        this.inventoryCommandService = inventoryCommandService;
        this.inventoryQueryService = inventoryQueryService;
    }

    @PostMapping("/variants/{variantId}/inventory")
    @Operation(summary = "Create inventory for a variant",
               description = "Creates inventory for a variant. Variant must exist and be enabled. " +
                           "Inventory determines whether a variant is purchasable. " +
                           "If inventory already exists for the variant and warehouse combination, returns conflict error.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inventory created successfully",
                    content = @Content(schema = @Schema(implementation = InventoryAdminResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data or variant not found/disabled"),
            @ApiResponse(responseCode = "409", description = "Inventory already exists for variant and warehouse")
    })
    public ResponseEntity<InventoryAdminResponseDto> createInventory(
            @PathVariable UUID variantId,
            @Valid @RequestBody InventoryCreateRequestDto request) {
        InventoryAdminResponseDto created = inventoryCommandService.createInventory(variantId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/inventory/{inventoryId}")
    @Operation(summary = "Update inventory",
               description = "Updates existing inventory. Only quantity and reservedQuantity can be updated. " +
                           "warehouseId and variantId cannot be changed. " +
                           "availableQuantity and inStock are automatically recalculated.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventory updated successfully",
                    content = @Content(schema = @Schema(implementation = InventoryAdminResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Inventory not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request data (e.g., quantity < reservedQuantity)")
    })
    public ResponseEntity<InventoryAdminResponseDto> updateInventory(
            @PathVariable UUID inventoryId,
            @Valid @RequestBody InventoryUpdateRequestDto request) {
        InventoryAdminResponseDto updated = inventoryCommandService.updateInventory(inventoryId, request);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/inventory")
    @Operation(summary = "Get all inventories",
               description = "Returns a list of all inventory records in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all inventories",
                    content = @Content(schema = @Schema(implementation = InventoryAdminResponseDto.class)))
    })
    public ResponseEntity<List<InventoryAdminResponseDto>> getAllInventories() {
        List<InventoryAdminResponseDto> inventories = inventoryQueryService.getAllInventories();
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/inventory/{inventoryId}")
    @Operation(summary = "Get inventory by ID",
               description = "Returns a single inventory record by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved inventory",
                    content = @Content(schema = @Schema(implementation = InventoryAdminResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Inventory not found")
    })
    public ResponseEntity<InventoryAdminResponseDto> getInventoryById(@PathVariable UUID inventoryId) {
        InventoryAdminResponseDto inventory = inventoryQueryService.getInventoryById(inventoryId);
        return ResponseEntity.ok(inventory);
    }

    @GetMapping("/variants/{variantId}/inventory")
    @Operation(summary = "Get all inventories for a variant",
               description = "Returns all inventory records for a variant. A variant can have multiple inventory records for different warehouses.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved inventories",
                    content = @Content(schema = @Schema(implementation = InventoryAdminResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Variant not found")
    })
    public ResponseEntity<List<InventoryAdminResponseDto>> getInventoriesByVariantId(@PathVariable UUID variantId) {
        List<InventoryAdminResponseDto> inventories = inventoryQueryService.getInventoriesByVariantId(variantId);
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/products/{productId}/inventory")
    @Operation(summary = "Get all inventories for a product",
               description = "Returns all inventory records for all variants of a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved inventories",
                    content = @Content(schema = @Schema(implementation = InventoryAdminResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<List<InventoryAdminResponseDto>> getInventoriesByProductId(@PathVariable UUID productId) {
        List<InventoryAdminResponseDto> inventories = inventoryQueryService.getInventoriesByProductId(productId);
        return ResponseEntity.ok(inventories);
    }
}

