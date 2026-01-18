package com.apna_dukan_backend.catalog.layout.api.admin;

import com.apna_dukan_backend.catalog.layout.application.*;
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

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/catalog/layout")
@Tag(name = "Catalog Layout Admin", description = "Admin API for managing catalog layout sections")
public class CatalogLayoutAdminController {
    private final CatalogLayoutQueryService catalogLayoutQueryService;
    private final CatalogLayoutCommandService catalogLayoutCommandService;

    public CatalogLayoutAdminController(CatalogLayoutQueryService catalogLayoutQueryService,
                                       CatalogLayoutCommandService catalogLayoutCommandService) {
        this.catalogLayoutQueryService = catalogLayoutQueryService;
        this.catalogLayoutCommandService = catalogLayoutCommandService;
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

    @PostMapping
    @Operation(summary = "Create new catalog section", description = "Creates a new catalog layout section")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Section created successfully",
                    content = @Content(schema = @Schema(implementation = CatalogSectionDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    public ResponseEntity<CatalogSectionDto> createSection(@Valid @RequestBody CreateSectionRequest request) {
        CatalogSectionDto created = catalogLayoutCommandService.createSection(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{sectionId}")
    @Operation(summary = "Update catalog section", description = "Updates an existing catalog layout section")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Section updated successfully",
                    content = @Content(schema = @Schema(implementation = CatalogSectionDto.class))),
            @ApiResponse(responseCode = "404", description = "Section not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    public ResponseEntity<CatalogSectionDto> updateSection(
            @PathVariable UUID sectionId,
            @Valid @RequestBody UpdateSectionRequest request) {
        CatalogSectionDto updated = catalogLayoutCommandService.updateSection(sectionId, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{sectionId}")
    @Operation(summary = "Delete catalog section", description = "Deletes a catalog layout section")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Section deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Section not found")
    })
    public ResponseEntity<Void> deleteSection(@PathVariable UUID sectionId) {
        catalogLayoutCommandService.deleteSection(sectionId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/bulk")
    @Operation(summary = "Bulk update catalog sections", description = "Updates multiple catalog sections at once (enabled, personalized, displayOrder)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sections updated successfully",
                    content = @Content(schema = @Schema(implementation = CatalogSectionDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    public ResponseEntity<List<CatalogSectionDto>> bulkUpdate(@Valid @RequestBody BulkUpdateRequest request) {
        List<CatalogSectionDto> updated = catalogLayoutCommandService.bulkUpdate(request);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{sectionId}/enable")
    @Operation(summary = "Enable catalog section", description = "Quickly enables a catalog section")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Section enabled successfully",
                    content = @Content(schema = @Schema(implementation = CatalogSectionDto.class))),
            @ApiResponse(responseCode = "404", description = "Section not found")
    })
    public ResponseEntity<CatalogSectionDto> enableSection(@PathVariable UUID sectionId) {
        CatalogSectionDto updated = catalogLayoutCommandService.enableDisable(sectionId, true);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{sectionId}/disable")
    @Operation(summary = "Disable catalog section", description = "Quickly disables a catalog section")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Section disabled successfully",
                    content = @Content(schema = @Schema(implementation = CatalogSectionDto.class))),
            @ApiResponse(responseCode = "404", description = "Section not found")
    })
    public ResponseEntity<CatalogSectionDto> disableSection(@PathVariable UUID sectionId) {
        CatalogSectionDto updated = catalogLayoutCommandService.enableDisable(sectionId, false);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{sectionId}/toggle")
    @Operation(summary = "Toggle catalog section enabled status", description = "Toggles the enabled status of a catalog section")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Section status toggled successfully",
                    content = @Content(schema = @Schema(implementation = CatalogSectionDto.class))),
            @ApiResponse(responseCode = "404", description = "Section not found")
    })
    public ResponseEntity<CatalogSectionDto> toggleSection(@PathVariable UUID sectionId) {
        CatalogSectionDto updated = catalogLayoutCommandService.toggleSection(sectionId);
        return ResponseEntity.ok(updated);
    }
}

