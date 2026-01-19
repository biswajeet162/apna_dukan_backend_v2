package com.apna_dukan_backend.catalog.productgroup.controller.user;

import com.apna_dukan_backend.catalog.productgroup.dto.ProductGroupResponseDto;
import com.apna_dukan_backend.catalog.productgroup.service.ProductGroupQueryService;
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
@RequestMapping("/api/v1/subCategory")
@Tag(name = "ProductGroup", description = "User API for retrieving product groups within subcategories")
public class ProductGroupController {
    private final ProductGroupQueryService productGroupQueryService;

    public ProductGroupController(ProductGroupQueryService productGroupQueryService) {
        this.productGroupQueryService = productGroupQueryService;
    }

    @GetMapping("/{subCategoryId}/productGroups")
    @Operation(summary = "Get enabled product groups for a subcategory",
               description = "Returns only enabled product groups for a subcategory, sorted by displayOrder")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved enabled product groups",
                    content = @Content(schema = @Schema(implementation = ProductGroupResponseDto.class),
                            examples = {
                                    @ExampleObject(name = "Sample Response with Enabled Product Groups Only",
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
                                                    "      \"imageUrl\": [\"https://example.com/image1.jpg\", \"https://example.com/image2.jpg\"]\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "      \"productGroupId\": \"g2b3c4d5-e6f7-4890-g123-456789012345\",\n" +
                                                    "      \"subCategoryId\": \"a1b2c3d4-e5f6-4789-a012-345678901234\",\n" +
                                                    "      \"name\": \"Fresh Vegetables\",\n" +
                                                    "      \"description\": \"Fresh seasonal vegetables\",\n" +
                                                    "      \"code\": \"FRESH_VEGETABLES\",\n" +
                                                    "      \"displayOrder\": 2,\n" +
                                                    "      \"enabled\": true,\n" +
                                                    "      \"imageUrl\": [\"https://example.com/vegetables.jpg\"]\n" +
                                                    "    }\n" +
                                                    "  ]\n" +
                                                    "}")
                            })),
            @ApiResponse(responseCode = "404", description = "SubCategory not found")
    })
    public ResponseEntity<ProductGroupResponseDto> getProductGroups(@PathVariable UUID subCategoryId) {
        ProductGroupResponseDto response = productGroupQueryService.getProductGroupsForUser(subCategoryId);
        return ResponseEntity.ok(response);
    }
}






