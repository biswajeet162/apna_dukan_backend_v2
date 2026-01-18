package com.apna_dukan_backend.catalog.category.controller;

import com.apna_dukan_backend.catalog.category.dto.CategorySectionResponseDto;
import com.apna_dukan_backend.catalog.category.service.CategoryQueryService;
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
@RequestMapping("/api/v1/section")
@Tag(name = "Category", description = "User API for retrieving product categories within sections")
public class CategoryController {
    private final CategoryQueryService categoryQueryService;

    public CategoryController(CategoryQueryService categoryQueryService) {
        this.categoryQueryService = categoryQueryService;
    }

    @GetMapping("/{sectionId}/categories")
    @Operation(summary = "Get enabled product categories for a section", 
               description = "Returns only enabled categories for a section with sectionCode = PRODUCT_CATEGORY, sorted by displayOrder")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved enabled categories",
                    content = @Content(schema = @Schema(implementation = CategorySectionResponseDto.class),
                            examples = {
                                    @ExampleObject(name = "Sample Response with Enabled Categories Only",
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
                                                    "      \"enabled\": true\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "      \"categoryId\": \"b2c3d4e5-f6a7-4890-b123-456789012345\",\n" +
                                                    "      \"sectionId\": \"768d4ee5-0ef7-458d-9748-409260cf93aa\",\n" +
                                                    "      \"name\": \"Electronics\",\n" +
                                                    "      \"description\": \"Mobile phones, laptops, gadgets, and electronic accessories\",\n" +
                                                    "      \"code\": \"ELECTRONICS\",\n" +
                                                    "      \"displayOrder\": 2,\n" +
                                                    "      \"enabled\": true\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "      \"categoryId\": \"c3d4e5f6-a7b8-4901-c234-567890123456\",\n" +
                                                    "      \"sectionId\": \"768d4ee5-0ef7-458d-9748-409260cf93aa\",\n" +
                                                    "      \"name\": \"Fashion\",\n" +
                                                    "      \"description\": \"Clothing, shoes, accessories, and fashion items for men and women\",\n" +
                                                    "      \"code\": \"FASHION\",\n" +
                                                    "      \"displayOrder\": 3,\n" +
                                                    "      \"enabled\": true\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "      \"categoryId\": \"d4e5f6a7-b8c9-4012-d345-678901234567\",\n" +
                                                    "      \"sectionId\": \"768d4ee5-0ef7-458d-9748-409260cf93aa\",\n" +
                                                    "      \"name\": \"Home & Kitchen\",\n" +
                                                    "      \"description\": \"Home decor, kitchen appliances, furniture, and household items\",\n" +
                                                    "      \"code\": \"HOME_KITCHEN\",\n" +
                                                    "      \"displayOrder\": 4,\n" +
                                                    "      \"enabled\": true\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "      \"categoryId\": \"e5f6a7b8-c9d0-4123-e456-789012345678\",\n" +
                                                    "      \"sectionId\": \"768d4ee5-0ef7-458d-9748-409260cf93aa\",\n" +
                                                    "      \"name\": \"Beauty & Personal Care\",\n" +
                                                    "      \"description\": \"Skincare, makeup, personal hygiene, and beauty products\",\n" +
                                                    "      \"code\": \"BEAUTY_CARE\",\n" +
                                                    "      \"displayOrder\": 5,\n" +
                                                    "      \"enabled\": true\n" +
                                                    "    }\n" +
                                                    "  ]\n" +
                                                    "}")
                            })),
            @ApiResponse(responseCode = "404", description = "Section not found"),
            @ApiResponse(responseCode = "400", description = "Invalid section code (not PRODUCT_CATEGORY)")
    })
    public ResponseEntity<CategorySectionResponseDto> getProductCategories(@PathVariable UUID sectionId) {
        CategorySectionResponseDto response = categoryQueryService.getCategoriesForUser(sectionId);
        return ResponseEntity.ok(response);
    }
}
