package com.apna_dukan_backend.catalog.category.controller.admin;

import com.apna_dukan_backend.catalog.category.dto.CategorySectionResponseDto;
import com.apna_dukan_backend.catalog.category.service.CategoryQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
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
                    content = @Content(schema = @Schema(implementation = CategorySectionResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Section not found"),
            @ApiResponse(responseCode = "400", description = "Invalid section code (not PRODUCT_CATEGORY)")
    })
    public ResponseEntity<CategorySectionResponseDto> getProductCategories(@PathVariable UUID sectionId) {
        CategorySectionResponseDto response = categoryQueryService.getCategoriesForAdmin(sectionId);
        return ResponseEntity.ok(response);
    }
}
