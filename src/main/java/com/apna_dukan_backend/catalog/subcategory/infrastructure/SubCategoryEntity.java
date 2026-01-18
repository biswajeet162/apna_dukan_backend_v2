package com.apna_dukan_backend.catalog.subcategory.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "subcategories")
public class SubCategoryEntity {
    @Id
    private String id;
    
    // TODO: Add entity fields
}

