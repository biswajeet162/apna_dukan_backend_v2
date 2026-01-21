package com.apna_dukan_backend.catalog.productmetrics.controller.system;

import com.apna_dukan_backend.catalog.productmetrics.model.dto.RatingUpdateRequestDto;
import com.apna_dukan_backend.catalog.productmetrics.service.ProductMetricsCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/system/product-metrics")
@Tag(name = "Product Metrics System", description = "System API for updating product metrics (internal use)")
public class ProductMetricsSystemController {
    private final ProductMetricsCommandService commandService;

    public ProductMetricsSystemController(ProductMetricsCommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping("/{productId}/purchase")
    @Operation(summary = "Increment purchase count",
               description = "Increment the total purchase count for a product. Called by order service when an order is placed.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Purchase count incremented successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<Void> incrementPurchase(@PathVariable UUID productId) {
        commandService.incrementPurchase(productId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{productId}/rating")
    @Operation(summary = "Add rating",
               description = "Add a rating to a product and recalculate average rating. Called by review service when a review is submitted.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rating added successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "400", description = "Invalid rating value")
    })
    public ResponseEntity<Void> addRating(
            @PathVariable UUID productId,
            @Valid @RequestBody RatingUpdateRequestDto request) {
        commandService.addRating(productId, request.getRating());
        return ResponseEntity.ok().build();
    }
}

