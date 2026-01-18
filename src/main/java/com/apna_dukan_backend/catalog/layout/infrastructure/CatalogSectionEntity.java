package com.apna_dukan_backend.catalog.layout.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "catalog_sections")
public class CatalogSectionEntity {
    @Id
    private String id;
    
    // TODO: Add entity fields
}

