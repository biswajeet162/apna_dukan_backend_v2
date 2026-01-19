package com.apna_dukan_backend.catalog.product.controller.user;

import com.apna_dukan_backend.catalog.product.model.dto.ProductDetailsDto;
import com.apna_dukan_backend.catalog.product.service.ProductDetailsQueryService;
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
@RequestMapping("/api/v1/product")
@Tag(name = "Product Details", description = "User API for retrieving product details (PDP)")
public class ProductDetailsController {
    private final ProductDetailsQueryService productDetailsQueryService;

    public ProductDetailsController(ProductDetailsQueryService productDetailsQueryService) {
        this.productDetailsQueryService = productDetailsQueryService;
    }

    @GetMapping("/{productId}")
    @Operation(summary = "Get product details (PDP)",
               description = "Returns complete product details including all enabled variants with their pricing " +
                           "and availability. Only enabled products and enabled variants with active pricing are included. " +
                           "Variants without pricing are skipped.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved product details",
                    content = @Content(schema = @Schema(implementation = ProductDetailsDto.class),
                            examples = {
                                    @ExampleObject(name = "Sample Product Details Response",
                                            value = "{\n" +
                                                    "  \"productId\": \"f1a2b3c4-d5e6-4789-f012-345678901234\",\n" +
                                                    "  \"name\": \"Apple - Red Delicious\",\n" +
                                                    "  \"brand\": \"Fresh Farm\",\n" +
                                                    "  \"description\": null,\n" +
                                                    "  \"images\": {\n" +
                                                    "    \"primary\": \"https://example.com/apple.jpg\",\n" +
                                                    "    \"gallery\": []\n" +
                                                    "  },\n" +
                                                    "  \"variants\": [\n" +
                                                    "    {\n" +
                                                    "      \"variantId\": \"b1a2b3c4-d5e6-4789-b012-345678901234\",\n" +
                                                    "      \"label\": \"1 kg\",\n" +
                                                    "      \"attributes\": {},\n" +
                                                    "      \"pricing\": {\n" +
                                                    "        \"sellingPrice\": 80.0,\n" +
                                                    "        \"mrp\": 100.0,\n" +
                                                    "        \"discountPercent\": 20,\n" +
                                                    "        \"currency\": \"INR\"\n" +
                                                    "      },\n" +
                                                    "      \"availability\": {\n" +
                                                    "        \"inStock\": true,\n" +
                                                    "        \"availableQuantity\": 0\n" +
                                                    "      }\n" +
                                                    "    }\n" +
                                                    "  ]\n" +
                                                    "}")
                            })),
            @ApiResponse(responseCode = "404", description = "Product not found or disabled")
    })
    public ResponseEntity<ProductDetailsDto> getProductDetails(@PathVariable UUID productId) {
        ProductDetailsDto response = productDetailsQueryService.getProductDetails(productId);
        return ResponseEntity.ok(response);
    }
}





