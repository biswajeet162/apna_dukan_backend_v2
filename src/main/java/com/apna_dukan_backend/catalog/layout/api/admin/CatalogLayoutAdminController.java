package com.apna_dukan_backend.catalog.layout.api.admin;

import com.apna_dukan_backend.catalog.layout.application.CatalogLayoutQueryService;
import com.apna_dukan_backend.catalog.layout.application.CatalogSectionDto;
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
@RequestMapping("/api/admin/catalog/layout")
@Tag(name = "Catalog Layout Admin", description = "Admin API for managing catalog layout sections")
public class CatalogLayoutAdminController {
    private final CatalogLayoutQueryService catalogLayoutQueryService;

    public CatalogLayoutAdminController(CatalogLayoutQueryService catalogLayoutQueryService) {
        this.catalogLayoutQueryService = catalogLayoutQueryService;
    }

    @GetMapping
    @Operation(summary = "Get all catalog sections", description = "Returns all catalog sections (enabled and disabled) sorted by display order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all sections",
                    content = @Content(schema = @Schema(implementation = CatalogSectionDto.class)))
    })
    public ResponseEntity<List<CatalogSectionDto>> getAllSections() {
        List<CatalogSectionDto> sections = catalogLayoutQueryService.getAllSectionsForAdmin();
        return ResponseEntity.ok(sections);
    }
}

