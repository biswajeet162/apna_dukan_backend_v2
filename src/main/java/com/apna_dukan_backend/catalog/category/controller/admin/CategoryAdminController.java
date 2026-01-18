package com.apna_dukan_backend.catalog.category.controller.admin;

import com.apna_dukan_backend.catalog.category.dto.CategorySectionAdminResponseDto;
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
@RequestMapping("/api/v1/admin/section")
@Tag(name = "Category Admin", description = "Admin API for managing product categories within sections")
public class CategoryAdminController {
    private final CategoryQueryService categoryQueryService;

    public CategoryAdminController(CategoryQueryService categoryQueryService) {
        this.categoryQueryService = categoryQueryService;
    }

    @GetMapping("/{sectionId}/ProductCategories")
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
}
