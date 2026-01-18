package com.apna_dukan_backend.catalog.layout.api.user;

import com.apna_dukan_backend.catalog.layout.application.CatalogLayoutQueryService;
import com.apna_dukan_backend.catalog.layout.application.CatalogSectionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/catalog/layout")
public class CatalogLayoutController {
    private final CatalogLayoutQueryService catalogLayoutQueryService;

    public CatalogLayoutController(CatalogLayoutQueryService catalogLayoutQueryService) {
        this.catalogLayoutQueryService = catalogLayoutQueryService;
    }

    @GetMapping
    public ResponseEntity<List<CatalogSectionDto>> getEnabledSections() {
        List<CatalogSectionDto> sections = catalogLayoutQueryService.getEnabledSectionsForUser();
        return ResponseEntity.ok(sections);
    }
}

