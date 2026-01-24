package com.apna_dukan_backend.catalog.productgroup.controller.admin;

import com.apna_dukan_backend.catalog.productgroup.dto.BulkUpdateProductGroupRequest;
import com.apna_dukan_backend.catalog.productgroup.dto.CreateProductGroupRequest;
import com.apna_dukan_backend.catalog.productgroup.dto.ProductGroupAdminDto;
import com.apna_dukan_backend.catalog.productgroup.dto.ProductGroupAdminResponseDto;
import com.apna_dukan_backend.catalog.productgroup.dto.UpdateProductGroupRequest;
import com.apna_dukan_backend.catalog.productgroup.service.ProductGroupCommandService;
import com.apna_dukan_backend.catalog.productgroup.service.ProductGroupQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping({"/api/v1/admin/subCategory", "/api/admin/subCategory"})
@Tag(name = "ProductGroup Admin", description = "Admin API for managing product groups within subcategories")
public class ProductGroupAdminController {
    private final ProductGroupQueryService productGroupQueryService;
    private final ProductGroupCommandService productGroupCommandService;

    public ProductGroupAdminController(ProductGroupQueryService productGroupQueryService,
                                     ProductGroupCommandService productGroupCommandService) {
        this.productGroupQueryService = productGroupQueryService;
        this.productGroupCommandService = productGroupCommandService;
    }

    @GetMapping("/{subCategoryId}/productGroups")
    @Operation(summary = "Get all product groups for a subcategory",
               description = "Returns all product groups (enabled and disabled) for a subcategory, sorted by displayOrder")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved product groups",
                    content = @Content(schema = @Schema(implementation = ProductGroupAdminResponseDto.class),
                            examples = {
                                    @ExampleObject(name = "Sample Response with All Product Groups",
                                            value = "{\n" +
                                                    "  \"subCategoryId\": \"a1b2c3d4-e5f6-4789-a012-345678901234\",\n" +
                                                    "  \"subCategoryName\": \"Fruits & Vegetables\",\n" +
                                                    "  \"productGroups\": [\n" +
                                                    "    {\n" +
                                                    "      \"productGroupId\": \"f1a2b3c4-d5e6-4789-f012-345678901234\",\n" +
                                                    "      \"subCategoryId\": \"a1b2c3d4-e5f6-4789-a012-345678901234\",\n" +
                                                    "      \"name\": \"Fresh Fruits\",\n" +
                                                    "      \"description\": \"Fresh seasonal fruits\",\n" +
                                                    "      \"code\": \"FRESH_FRUITS\",\n" +
                                                    "      \"displayOrder\": 1,\n" +
                                                    "      \"enabled\": true,\n" +
                                                    "      \"imageUrl\": [\"https://example.com/image1.jpg\", \"https://example.com/image2.jpg\"],\n" +
                                                    "      \"createdAt\": \"2026-01-17T10:35:00.000000\",\n" +
                                                    "      \"updatedAt\": \"2026-01-17T10:35:00.000000\"\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "      \"productGroupId\": \"g2b3c4d5-e6f7-4890-g123-456789012345\",\n" +
                                                    "      \"subCategoryId\": \"a1b2c3d4-e5f6-4789-a012-345678901234\",\n" +
                                                    "      \"name\": \"Fresh Vegetables\",\n" +
                                                    "      \"description\": \"Fresh seasonal vegetables\",\n" +
                                                    "      \"code\": \"FRESH_VEGETABLES\",\n" +
                                                    "      \"displayOrder\": 2,\n" +
                                                    "      \"enabled\": true,\n" +
                                                    "      \"imageUrl\": [\"https://example.com/vegetables.jpg\"],\n" +
                                                    "      \"createdAt\": \"2026-01-17T10:35:00.000000\",\n" +
                                                    "      \"updatedAt\": \"2026-01-17T10:35:00.000000\"\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "      \"productGroupId\": \"h3c4d5e6-f7a8-4901-h234-567890123456\",\n" +
                                                    "      \"subCategoryId\": \"a1b2c3d4-e5f6-4789-a012-345678901234\",\n" +
                                                    "      \"name\": \"Organic Produce\",\n" +
                                                    "      \"description\": \"Organic fruits and vegetables\",\n" +
                                                    "      \"code\": \"ORGANIC_PRODUCE\",\n" +
                                                    "      \"displayOrder\": 3,\n" +
                                                    "      \"enabled\": false,\n" +
                                                    "      \"imageUrl\": [],\n" +
                                                    "      \"createdAt\": \"2026-01-17T10:35:00.000000\",\n" +
                                                    "      \"updatedAt\": \"2026-01-17T10:35:00.000000\"\n" +
                                                    "    }\n" +
                                                    "  ]\n" +
                                                    "}")
                            })),
            @ApiResponse(responseCode = "404", description = "SubCategory not found")
    })
    public ResponseEntity<ProductGroupAdminResponseDto> getProductGroups(@PathVariable UUID subCategoryId) {
        ProductGroupAdminResponseDto response = productGroupQueryService.getProductGroupsForAdmin(subCategoryId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/productGroup/{productGroupId}")
    @Operation(summary = "Get product group by ID",
               description = "Returns a single product group by its ID with all details including timestamps")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved product group",
                    content = @Content(schema = @Schema(implementation = ProductGroupAdminDto.class))),
            @ApiResponse(responseCode = "404", description = "ProductGroup not found")
    })
    public ResponseEntity<ProductGroupAdminDto> getProductGroupById(@PathVariable UUID productGroupId) {
        ProductGroupAdminDto productGroup = productGroupQueryService.getProductGroupById(productGroupId);
        return ResponseEntity.ok(productGroup);
    }

    @PostMapping("/productGroup")
    @Operation(summary = "Create a new product group",
               description = "Creates a new product group for a subcategory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ProductGroup created successfully",
                    content = @Content(schema = @Schema(implementation = ProductGroupAdminDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "SubCategory not found")
    })
    public ResponseEntity<ProductGroupAdminDto> createProductGroup(@Valid @RequestBody CreateProductGroupRequest request) {
        ProductGroupAdminDto created = productGroupCommandService.createProductGroup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/productGroup/{productGroupId}")
    @Operation(summary = "Update a product group",
               description = "Updates an existing product group. Only provided fields will be updated.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ProductGroup updated successfully",
                    content = @Content(schema = @Schema(implementation = ProductGroupAdminDto.class))),
            @ApiResponse(responseCode = "404", description = "ProductGroup not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    public ResponseEntity<ProductGroupAdminDto> updateProductGroup(
            @PathVariable UUID productGroupId,
            @Valid @RequestBody UpdateProductGroupRequest request) {
        ProductGroupAdminDto updated = productGroupCommandService.updateProductGroup(productGroupId, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/productGroup/{productGroupId}")
    @Operation(summary = "Delete a product group",
               description = "Deletes a product group by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "ProductGroup deleted successfully"),
            @ApiResponse(responseCode = "404", description = "ProductGroup not found")
    })
    public ResponseEntity<Void> deleteProductGroup(@PathVariable UUID productGroupId) {
        productGroupCommandService.deleteProductGroup(productGroupId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/productGroup/bulk")
    @Operation(summary = "Bulk update product groups",
               description = "Updates multiple product groups at once. Each product group can have different field values. Only provided fields will be updated.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ProductGroups updated successfully",
                    content = @Content(schema = @Schema(implementation = ProductGroupAdminDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Some product groups not found")
    })
    public ResponseEntity<List<ProductGroupAdminDto>> bulkUpdateProductGroups(
            @Valid @RequestBody BulkUpdateProductGroupRequest request) {
        List<ProductGroupAdminDto> updated = productGroupCommandService.bulkUpdate(request);
        return ResponseEntity.ok(updated);
    }
}

