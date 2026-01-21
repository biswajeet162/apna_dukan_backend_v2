package com.apna_dukan_backend.catalog.productmetrics.controller.admin;

import com.apna_dukan_backend.catalog.productmetrics.model.dto.ProductMetricsAdminDto;
import com.apna_dukan_backend.catalog.productmetrics.model.dto.ProductMetricsUpdateRequestDto;
import com.apna_dukan_backend.catalog.productmetrics.model.domain.ProductMetrics;
import com.apna_dukan_backend.catalog.productmetrics.service.ProductMetricsCommandService;
import com.apna_dukan_backend.catalog.productmetrics.service.ProductMetricsQueryService;
import com.apna_dukan_backend.catalog.productmetrics.service.mapper.ProductMetricsMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/product-metrics")
@Tag(name = "Product Metrics Admin", description = "Admin API for managing product metrics")
public class ProductMetricsAdminController {
    private final ProductMetricsQueryService queryService;
    private final ProductMetricsCommandService commandService;
    private final ProductMetricsMapper mapper;

    public ProductMetricsAdminController(
            ProductMetricsQueryService queryService,
            ProductMetricsCommandService commandService,
            ProductMetricsMapper mapper) {
        this.queryService = queryService;
        this.commandService = commandService;
        this.mapper = mapper;
    }

    @GetMapping("/{productId}")
    @Operation(summary = "Get product metrics",
               description = "Returns metrics for a product including average rating, rating count, and total purchases")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved metrics",
                    content = @Content(schema = @Schema(implementation = ProductMetricsAdminDto.class))),
            @ApiResponse(responseCode = "404", description = "Product not found or metrics not available")
    })
    public ResponseEntity<ProductMetricsAdminDto> getMetrics(@PathVariable UUID productId) {
        ProductMetricsAdminDto metrics = queryService.getMetricsByProductId(productId);
        
        if (metrics == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(metrics);
    }

    @PutMapping("/{productId}")
    @Operation(summary = "Update product metrics",
               description = "Manually update product metrics. Used for correction or migration purposes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Metrics updated successfully",
                    content = @Content(schema = @Schema(implementation = ProductMetricsAdminDto.class))),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    public ResponseEntity<ProductMetricsAdminDto> updateMetrics(
            @PathVariable UUID productId,
            @Valid @RequestBody ProductMetricsUpdateRequestDto request) {
        ProductMetrics updated = commandService.adminUpdateMetrics(productId, request);
        ProductMetricsAdminDto dto = mapper.toAdminDto(updated);
        return ResponseEntity.ok(dto);
    }
}

