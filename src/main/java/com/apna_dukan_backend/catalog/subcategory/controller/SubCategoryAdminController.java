package com.apna_dukan_backend.catalog.subcategory.controller.admin;

import com.apna_dukan_backend.catalog.subcategory.dto.BulkUpdateSubCategoryRequest;
import com.apna_dukan_backend.catalog.subcategory.dto.CreateSubCategoryRequest;
import com.apna_dukan_backend.catalog.subcategory.dto.SubCategoryAdminDto;
import com.apna_dukan_backend.catalog.subcategory.dto.SubCategoryAdminResponseDto;
import com.apna_dukan_backend.catalog.subcategory.dto.UpdateSubCategoryRequest;
import com.apna_dukan_backend.catalog.subcategory.service.SubCategoryCommandService;
import com.apna_dukan_backend.catalog.subcategory.service.SubCategoryQueryService;
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
@RequestMapping("/api/v1/admin/category")
@Tag(name = "SubCategory Admin", description = "Admin API for managing subcategories within categories")
public class SubCategoryAdminController {
    private final SubCategoryQueryService subCategoryQueryService;
    private final SubCategoryCommandService subCategoryCommandService;

    public SubCategoryAdminController(SubCategoryQueryService subCategoryQueryService,
                                     SubCategoryCommandService subCategoryCommandService) {
        this.subCategoryQueryService = subCategoryQueryService;
        this.subCategoryCommandService = subCategoryCommandService;
    }

    @GetMapping("/{categoryId}/subCategories")
    @Operation(summary = "Get all subcategories for a category",
               description = "Returns all subcategories (enabled and disabled) for a category, sorted by displayOrder")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved subcategories",
                    content = @Content(schema = @Schema(implementation = SubCategoryAdminResponseDto.class),
                            examples = {
                                    @ExampleObject(name = "Sample Response with All SubCategories",
                                            value = "{\n" +
                                                    "  \"categoryId\": \"a1b2c3d4-e5f6-4789-a012-345678901234\",\n" +
                                                    "  \"categoryName\": \"Grocery\",\n" +
                                                    "  \"subCategories\": [\n" +
                                                    "    {\n" +
                                                    "      \"subCategoryId\": \"f1a2b3c4-d5e6-4789-f012-345678901234\",\n" +
                                                    "      \"categoryId\": \"a1b2c3d4-e5f6-4789-a012-345678901234\",\n" +
                                                    "      \"name\": \"Fruits & Vegetables\",\n" +
                                                    "      \"description\": \"Fresh fruits and vegetables\",\n" +
                                                    "      \"code\": \"FRUITS_VEGETABLES\",\n" +
                                                    "      \"displayOrder\": 1,\n" +
                                                    "      \"enabled\": true,\n" +
                                                    "      \"imageUrl\": [\"https://example.com/image1.jpg\", \"https://example.com/image2.jpg\"],\n" +
                                                    "      \"createdAt\": \"2026-01-17T10:35:00.000000\",\n" +
                                                    "      \"updatedAt\": \"2026-01-17T10:35:00.000000\"\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "      \"subCategoryId\": \"g2b3c4d5-e6f7-4890-g123-456789012345\",\n" +
                                                    "      \"categoryId\": \"a1b2c3d4-e5f6-4789-a012-345678901234\",\n" +
                                                    "      \"name\": \"Dairy Products\",\n" +
                                                    "      \"description\": \"Milk, cheese, yogurt and other dairy items\",\n" +
                                                    "      \"code\": \"DAIRY_PRODUCTS\",\n" +
                                                    "      \"displayOrder\": 2,\n" +
                                                    "      \"enabled\": true,\n" +
                                                    "      \"imageUrl\": [\"https://example.com/dairy.jpg\"],\n" +
                                                    "      \"createdAt\": \"2026-01-17T10:35:00.000000\",\n" +
                                                    "      \"updatedAt\": \"2026-01-17T10:35:00.000000\"\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "      \"subCategoryId\": \"h3c4d5e6-f7a8-4901-h234-567890123456\",\n" +
                                                    "      \"categoryId\": \"a1b2c3d4-e5f6-4789-a012-345678901234\",\n" +
                                                    "      \"name\": \"Frozen Foods\",\n" +
                                                    "      \"description\": \"Frozen food items\",\n" +
                                                    "      \"code\": \"FROZEN_FOODS\",\n" +
                                                    "      \"displayOrder\": 3,\n" +
                                                    "      \"enabled\": false,\n" +
                                                    "      \"imageUrl\": [],\n" +
                                                    "      \"createdAt\": \"2026-01-17T10:35:00.000000\",\n" +
                                                    "      \"updatedAt\": \"2026-01-17T10:35:00.000000\"\n" +
                                                    "    }\n" +
                                                    "  ]\n" +
                                                    "}")
                            })),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<SubCategoryAdminResponseDto> getSubCategories(@PathVariable UUID categoryId) {
        SubCategoryAdminResponseDto response = subCategoryQueryService.getSubCategoriesForAdmin(categoryId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/subCategory/{subCategoryId}")
    @Operation(summary = "Get subcategory by ID",
               description = "Returns a single subcategory by its ID with all details including timestamps")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved subcategory",
                    content = @Content(schema = @Schema(implementation = SubCategoryAdminDto.class))),
            @ApiResponse(responseCode = "404", description = "SubCategory not found")
    })
    public ResponseEntity<SubCategoryAdminDto> getSubCategoryById(@PathVariable UUID subCategoryId) {
        SubCategoryAdminDto subCategory = subCategoryQueryService.getSubCategoryById(subCategoryId);
        return ResponseEntity.ok(subCategory);
    }

    @PostMapping("/subCategory")
    @Operation(summary = "Create a new subcategory",
               description = "Creates a new subcategory for a category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "SubCategory created successfully",
                    content = @Content(schema = @Schema(implementation = SubCategoryAdminDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<SubCategoryAdminDto> createSubCategory(@Valid @RequestBody CreateSubCategoryRequest request) {
        SubCategoryAdminDto created = subCategoryCommandService.createSubCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/subCategory/{subCategoryId}")
    @Operation(summary = "Update a subcategory",
               description = "Updates an existing subcategory. Only provided fields will be updated.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SubCategory updated successfully",
                    content = @Content(schema = @Schema(implementation = SubCategoryAdminDto.class))),
            @ApiResponse(responseCode = "404", description = "SubCategory not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    public ResponseEntity<SubCategoryAdminDto> updateSubCategory(
            @PathVariable UUID subCategoryId,
            @Valid @RequestBody UpdateSubCategoryRequest request) {
        SubCategoryAdminDto updated = subCategoryCommandService.updateSubCategory(subCategoryId, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/subCategory/{subCategoryId}")
    @Operation(summary = "Delete a subcategory",
               description = "Deletes a subcategory by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "SubCategory deleted successfully"),
            @ApiResponse(responseCode = "404", description = "SubCategory not found")
    })
    public ResponseEntity<Void> deleteSubCategory(@PathVariable UUID subCategoryId) {
        subCategoryCommandService.deleteSubCategory(subCategoryId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/subCategory/bulk")
    @Operation(summary = "Bulk update subcategories",
               description = "Updates multiple subcategories at once. Each subcategory can have different field values. Only provided fields will be updated.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SubCategories updated successfully",
                    content = @Content(schema = @Schema(implementation = SubCategoryAdminDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Some subcategories not found")
    })
    public ResponseEntity<List<SubCategoryAdminDto>> bulkUpdateSubCategories(
            @Valid @RequestBody BulkUpdateSubCategoryRequest request) {
        List<SubCategoryAdminDto> updated = subCategoryCommandService.bulkUpdate(request);
        return ResponseEntity.ok(updated);
    }
}
