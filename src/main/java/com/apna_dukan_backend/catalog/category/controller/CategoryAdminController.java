package com.apna_dukan_backend.catalog.category.controller;

import com.apna_dukan_backend.catalog.category.dto.*;
import com.apna_dukan_backend.catalog.category.service.CategoryCommandService;
import com.apna_dukan_backend.catalog.category.service.CategoryQueryService;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin")
@Tag(name = "Category Admin", description = "Admin API for managing product categories within sections")
public class CategoryAdminController {
    private final CategoryQueryService categoryQueryService;
    private final CategoryCommandService categoryCommandService;

    public CategoryAdminController(CategoryQueryService categoryQueryService,
                                  CategoryCommandService categoryCommandService) {
        this.categoryQueryService = categoryQueryService;
        this.categoryCommandService = categoryCommandService;
    }

    @GetMapping("/section/{sectionId}/categories")
    @Operation(summary = "Get all product categories for a section", 
               description = "Returns all categories (enabled and disabled) for a section with sectionCode = PRODUCT_CATEGORY")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved categories",
                    content = @Content(schema = @Schema(implementation = CategorySectionAdminResponseDto.class),
                            examples = {
                                    @ExampleObject(name = "Sample Response with All Categories",
                                            value = "{\n" +
                                                    "  \"sectionId\": \"768d4ee5-0ef7-458d-9748-409260cf93aa\",\n" +
                                                    "  \"sectionCode\": \"PRODUCT_CATEGORY\",\n" +
                                                    "  \"categories\": [\n" +
                                                    "    {\n" +
                                                    "      \"categoryId\": \"a1b2c3d4-e5f6-4789-a012-345678901234\",\n" +
                                                    "      \"sectionId\": \"768d4ee5-0ef7-458d-9748-409260cf93aa\",\n" +
                                                    "      \"name\": \"Grocery\",\n" +
                                                    "      \"description\": \"Fresh fruits, vegetables, dairy products, and daily essentials\",\n" +
                                                    "      \"code\": \"GROCERY\",\n" +
                                                    "      \"displayOrder\": 1,\n" +
                                                    "      \"enabled\": true,\n" +
                                                    "      \"createdAt\": \"2026-01-17T10:35:00.000000\",\n" +
                                                    "      \"updatedAt\": \"2026-01-17T10:35:00.000000\"\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "      \"categoryId\": \"b2c3d4e5-f6a7-4890-b123-456789012345\",\n" +
                                                    "      \"sectionId\": \"768d4ee5-0ef7-458d-9748-409260cf93aa\",\n" +
                                                    "      \"name\": \"Electronics\",\n" +
                                                    "      \"description\": \"Mobile phones, laptops, gadgets, and electronic accessories\",\n" +
                                                    "      \"code\": \"ELECTRONICS\",\n" +
                                                    "      \"displayOrder\": 2,\n" +
                                                    "      \"enabled\": true,\n" +
                                                    "      \"createdAt\": \"2026-01-17T10:35:00.000000\",\n" +
                                                    "      \"updatedAt\": \"2026-01-17T10:35:00.000000\"\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "      \"categoryId\": \"c3d4e5f6-a7b8-4901-c234-567890123456\",\n" +
                                                    "      \"sectionId\": \"768d4ee5-0ef7-458d-9748-409260cf93aa\",\n" +
                                                    "      \"name\": \"Fashion\",\n" +
                                                    "      \"description\": \"Clothing, shoes, accessories, and fashion items for men and women\",\n" +
                                                    "      \"code\": \"FASHION\",\n" +
                                                    "      \"displayOrder\": 3,\n" +
                                                    "      \"enabled\": true,\n" +
                                                    "      \"createdAt\": \"2026-01-17T10:35:00.000000\",\n" +
                                                    "      \"updatedAt\": \"2026-01-17T10:35:00.000000\"\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "      \"categoryId\": \"d6e7f8a9-b0c1-4234-d567-890123456789\",\n" +
                                                    "      \"sectionId\": \"768d4ee5-0ef7-458d-9748-409260cf93aa\",\n" +
                                                    "      \"name\": \"Luxury Items\",\n" +
                                                    "      \"description\": \"Premium luxury products and exclusive collections\",\n" +
                                                    "      \"code\": \"LUXURY_ITEMS\",\n" +
                                                    "      \"displayOrder\": 16,\n" +
                                                    "      \"enabled\": false,\n" +
                                                    "      \"createdAt\": \"2026-01-17T10:35:00.000000\",\n" +
                                                    "      \"updatedAt\": \"2026-01-17T10:35:00.000000\"\n" +
                                                    "    }\n" +
                                                    "  ]\n" +
                                                    "}")
                            })),
            @ApiResponse(responseCode = "404", description = "Section not found"),
            @ApiResponse(responseCode = "400", description = "Invalid section code (not PRODUCT_CATEGORY)")
    })
    public ResponseEntity<CategorySectionAdminResponseDto> getProductCategories(@PathVariable UUID sectionId) {
        CategorySectionAdminResponseDto response = categoryQueryService.getCategoriesForAdmin(sectionId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Get category by ID", 
               description = "Returns a single category by its ID with all details including timestamps")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved category",
                    content = @Content(schema = @Schema(implementation = CategoryAdminDto.class))),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<CategoryAdminDto> getCategoryById(@PathVariable UUID categoryId) {
        CategoryAdminDto category = categoryQueryService.getCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }

    @PostMapping("/category")
    @Operation(summary = "Create a new category", 
               description = "Creates a new category in a section with sectionCode = PRODUCT_CATEGORY")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully",
                    content = @Content(schema = @Schema(implementation = CategoryAdminDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data or invalid section code"),
            @ApiResponse(responseCode = "404", description = "Section not found")
    })
    public ResponseEntity<CategoryAdminDto> createCategory(@Valid @RequestBody CreateCategoryRequest request) {
        CategoryAdminDto created = categoryCommandService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/category/{categoryId}")
    @Operation(summary = "Update a category", 
               description = "Updates an existing category. Only provided fields will be updated.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category updated successfully",
                    content = @Content(schema = @Schema(implementation = CategoryAdminDto.class))),
            @ApiResponse(responseCode = "404", description = "Category not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    public ResponseEntity<CategoryAdminDto> updateCategory(
            @PathVariable UUID categoryId,
            @Valid @RequestBody UpdateCategoryRequest request) {
        CategoryAdminDto updated = categoryCommandService.updateCategory(categoryId, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/category/{categoryId}")
    @Operation(summary = "Delete a category", 
               description = "Deletes a category by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID categoryId) {
        categoryCommandService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/category/bulk")
    @Operation(summary = "Bulk update categories", 
               description = "Updates multiple categories at once. Each category can have different field values. Only provided fields will be updated.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories updated successfully",
                    content = @Content(schema = @Schema(implementation = CategoryAdminDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Some categories not found")
    })
    public ResponseEntity<List<CategoryAdminDto>> bulkUpdateCategories(
            @Valid @RequestBody BulkUpdateCategoryRequest request) {
        List<CategoryAdminDto> updated = categoryCommandService.bulkUpdate(request);
        return ResponseEntity.ok(updated);
    }
}
