package com.apna_dukan_backend.catalog.product.controller.user;

import com.apna_dukan_backend.catalog.product.model.dto.ProductListingResponseDto;
import com.apna_dukan_backend.catalog.product.service.ProductListingQueryService;
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
@RequestMapping("/api/v1/productGroup")
@Tag(name = "Product Listing", description = "User API for retrieving product listings within product groups")
public class ProductListingController {
    private final ProductListingQueryService productListingQueryService;

    public ProductListingController(ProductListingQueryService productListingQueryService) {
        this.productListingQueryService = productListingQueryService;
    }

    @GetMapping("/{productGroupId}/products")
    @Operation(summary = "Get product listing for a product group",
               description = "Returns a paginated, aggregated list of enabled products with their default variants, " +
                           "pricing, and availability. Products are sorted by displayOrder. " +
                           "Only products with enabled default variants and active pricing are included. " +
                           "Supports pagination with page (0-based) and size (default: 20, max: 100) query parameters.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved product listing",
                    content = @Content(schema = @Schema(implementation = ProductListingResponseDto.class),
                            examples = {
                                    @ExampleObject(name = "Sample Product Listing Response",
                                            value = "{\n" +
                                                    "  \"productGroupId\": \"a1a2b3c4-d5e6-4789-a012-345678901234\",\n" +
                                                    "  \"productGroupName\": \"Fresh Fruits\",\n" +
                                                    "  \"products\": [\n" +
                                                    "    {\n" +
                                                    "      \"productId\": \"f1a2b3c4-d5e6-4789-f012-345678901234\",\n" +
                                                    "      \"name\": \"Apple - Red Delicious\",\n" +
                                                    "      \"brand\": \"Fresh Farm\",\n" +
                                                    "      \"image\": {\n" +
                                                    "        \"primary\": \"https://example.com/apple.jpg\"\n" +
                                                    "      },\n" +
                                                    "      \"defaultVariant\": {\n" +
                                                    "        \"variantId\": \"b1a2b3c4-d5e6-4789-b012-345678901234\",\n" +
                                                    "        \"label\": \"1 kg\"\n" +
                                                    "      },\n" +
                                                    "      \"pricing\": {\n" +
                                                    "        \"sellingPrice\": 80.0,\n" +
                                                    "        \"mrp\": 100.0,\n" +
                                                    "        \"discountPercent\": 20,\n" +
                                                    "        \"currency\": \"INR\"\n" +
                                                    "      },\n" +
                                                    "      \"availability\": {\n" +
                                                    "        \"inStock\": true\n" +
                                                    "      }\n" +
                                                    "    }\n" +
                                                    "  ],\n" +
                                                    "  \"pagination\": {\n" +
                                                    "    \"page\": 0,\n" +
                                                    "    \"size\": 20,\n" +
                                                    "    \"totalElements\": 45,\n" +
                                                    "    \"totalPages\": 3,\n" +
                                                    "    \"hasNext\": true,\n" +
                                                    "    \"hasPrevious\": false\n" +
                                                    "  }\n" +
                                                    "}")
                            })),
            @ApiResponse(responseCode = "404", description = "ProductGroup not found")
    })
    public ResponseEntity<ProductListingResponseDto> getProductListing(
            @PathVariable UUID productGroupId,
            @org.springframework.web.bind.annotation.RequestParam(defaultValue = "0") int page,
            @org.springframework.web.bind.annotation.RequestParam(defaultValue = "20") int size) {
        ProductListingResponseDto response = productListingQueryService.getProductListing(productGroupId, page, size);
        return ResponseEntity.ok(response);
    }
}

