package com.apna_dukan_backend.catalog.variant.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "variants")
public class VariantEntity {
    @Id
    private String id;
    
    // TODO: Add entity fields
}

