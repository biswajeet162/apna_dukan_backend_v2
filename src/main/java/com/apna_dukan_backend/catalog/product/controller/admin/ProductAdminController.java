package com.apna_dukan_backend.catalog.product.controller.admin;

import com.apna_dukan_backend.catalog.product.model.dto.EnableDisableProductRequest;
import com.apna_dukan_backend.catalog.product.model.dto.ProductAdminResponseDto;
import com.apna_dukan_backend.catalog.product.model.dto.ProductCreateRequestDto;
import com.apna_dukan_backend.catalog.product.model.dto.ProductUpdateRequestDto;
import com.apna_dukan_backend.catalog.product.service.ProductCommandService;
import com.apna_dukan_backend.catalog.product.service.ProductQueryService;
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

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/products")
@Tag(name = "Product Admin", description = "Admin API for managing products")
public class ProductAdminController {
    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    public ProductAdminController(ProductCommandService productCommandService, ProductQueryService productQueryService) {
        this.productCommandService = productCommandService;
        this.productQueryService = productQueryService;
    }

    @GetMapping("/{productId}")
    @Operation(summary = "Get product by ID",
               description = "Returns a single product by its ID with all details including timestamps")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved product",
                    content = @Content(schema = @Schema(implementation = ProductAdminResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<ProductAdminResponseDto> getProductById(@PathVariable UUID productId) {
        ProductAdminResponseDto product = productQueryService.getProductById(productId);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    @Operation(summary = "Create a new product",
               description = "Creates a new product. Product will be disabled by default and is not sellable until variants, pricing, and inventory are added.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully",
                    content = @Content(schema = @Schema(implementation = ProductAdminResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data or ProductGroup not found"),
            @ApiResponse(responseCode = "409", description = "Product code already exists")
    })
    public ResponseEntity<ProductAdminResponseDto> createProduct(@Valid @RequestBody ProductCreateRequestDto request) {
        ProductAdminResponseDto created = productCommandService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{productId}")
    @Operation(summary = "Update a product",
               description = "Updates an existing product. Only provided fields will be updated. Code and productGroupId cannot be changed.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully",
                    content = @Content(schema = @Schema(implementation = ProductAdminResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    public ResponseEntity<ProductAdminResponseDto> updateProduct(
            @PathVariable UUID productId,
            @Valid @RequestBody ProductUpdateRequestDto request) {
        ProductAdminResponseDto updated = productCommandService.updateProduct(productId, request);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{productId}/enable")
    @Operation(summary = "Enable or disable a product",
               description = "Enables or disables a product. When disabled, product will disappear from user APIs.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product status updated successfully",
                    content = @Content(schema = @Schema(implementation = ProductAdminResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    public ResponseEntity<ProductAdminResponseDto> updateProductEnabledStatus(
            @PathVariable UUID productId,
            @Valid @RequestBody EnableDisableProductRequest request) {
        ProductAdminResponseDto updated = productCommandService.updateProductEnabledStatus(productId, request.getEnabled());
        return ResponseEntity.ok(updated);
    }
}

