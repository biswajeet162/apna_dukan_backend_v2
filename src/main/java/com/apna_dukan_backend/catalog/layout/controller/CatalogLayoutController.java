package com.apna_dukan_backend.catalog.layout.controller;

import com.apna_dukan_backend.catalog.layout.dto.CatalogSectionDto;
import com.apna_dukan_backend.catalog.layout.service.CatalogLayoutQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/catalog/layout")
@Tag(name = "Catalog Layout", description = "User API for retrieving catalog layout sections")
public class CatalogLayoutController {
    private final CatalogLayoutQueryService catalogLayoutQueryService;

    public CatalogLayoutController(CatalogLayoutQueryService catalogLayoutQueryService) {
        this.catalogLayoutQueryService = catalogLayoutQueryService;
    }

    @GetMapping
    @Operation(summary = "Get enabled catalog sections", description = "Returns only enabled catalog sections sorted by display order for UI rendering")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved enabled sections",
                    content = @Content(schema = @Schema(implementation = CatalogSectionDto.class)))
    })
    public ResponseEntity<List<CatalogSectionDto>> getEnabledSections() {
        List<CatalogSectionDto> sections = catalogLayoutQueryService.getEnabledSectionsForUser();
        return ResponseEntity.ok(sections);
    }
}






