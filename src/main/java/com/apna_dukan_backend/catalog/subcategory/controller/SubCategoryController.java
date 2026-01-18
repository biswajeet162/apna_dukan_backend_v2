package com.apna_dukan_backend.catalog.subcategory.controller;

import com.apna_dukan_backend.catalog.subcategory.dto.SubCategoryResponseDto;
import com.apna_dukan_backend.catalog.subcategory.service.SubCategoryQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/category")
@Tag(name = "SubCategory", description = "User API for retrieving subcategories within categories")
public class SubCategoryController {
    private final SubCategoryQueryService subCategoryQueryService;

    public SubCategoryController(SubCategoryQueryService subCategoryQueryService) {
        this.subCategoryQueryService = subCategoryQueryService;
    }

    @GetMapping("/{categoryId}/subCategories")
    @Operation(summary = "Get enabled subcategories for a category",
               description = "Returns only enabled subcategories for a category, sorted by displayOrder")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved enabled subcategories",
                    content = @Content(schema = @Schema(implementation = SubCategoryResponseDto.class),
                            examples = {
                                    @ExampleObject(name = "Sample Response with Enabled SubCategories Only",
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
                                                    "      \"imageUrl\": [\"https://images.unsplash.com/photo-1610832958506-aa56368176cf?w=800&h=600&fit=crop\", \"https://images.unsplash.com/photo-1560806887-1e4cd0b6cbd6?w=800&h=600&fit=crop\", \"https://images.unsplash.com/photo-1598170845058-32b9d6a5da37?w=800&h=600&fit=crop\"]\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "      \"subCategoryId\": \"a2b3c4d5-e6f7-4890-a123-456789012345\",\n" +
                                                    "      \"categoryId\": \"a1b2c3d4-e5f6-4789-a012-345678901234\",\n" +
                                                    "      \"name\": \"Dairy Products\",\n" +
                                                    "      \"description\": \"Milk, cheese, yogurt and other dairy items\",\n" +
                                                    "      \"code\": \"DAIRY_PRODUCTS\",\n" +
                                                    "      \"displayOrder\": 2,\n" +
                                                    "      \"enabled\": true,\n" +
                                                    "      \"imageUrl\": [\"https://images.unsplash.com/photo-1550583724-b2692b85b150?w=800&h=600&fit=crop\", \"https://images.unsplash.com/photo-1486297678162-eb2a19b0a32d?w=800&h=600&fit=crop\", \"https://images.unsplash.com/photo-1618164436269-4c11f564107e?w=800&h=600&fit=crop\"]\n" +
                                                    "    }\n" +
                                                    "  ]\n" +
                                                    "}")
                            })),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<SubCategoryResponseDto> getSubCategories(@PathVariable UUID categoryId) {
        SubCategoryResponseDto response = subCategoryQueryService.getSubCategoriesForUser(categoryId);
        return ResponseEntity.ok(response);
    }
}
