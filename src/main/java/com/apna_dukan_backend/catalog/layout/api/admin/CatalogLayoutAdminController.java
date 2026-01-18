package com.apna_dukan_backend.catalog.layout.api.admin;

import com.apna_dukan_backend.catalog.layout.application.CatalogLayoutQueryService;
import com.apna_dukan_backend.catalog.layout.application.CatalogSectionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/catalog/layout")
public class CatalogLayoutAdminController {
    private final CatalogLayoutQueryService catalogLayoutQueryService;

    public CatalogLayoutAdminController(CatalogLayoutQueryService catalogLayoutQueryService) {
        this.catalogLayoutQueryService = catalogLayoutQueryService;
    }

    @GetMapping
    public ResponseEntity<List<CatalogSectionDto>> getAllSections() {
        List<CatalogSectionDto> sections = catalogLayoutQueryService.getAllSectionsForAdmin();
        return ResponseEntity.ok(sections);
    }
}

