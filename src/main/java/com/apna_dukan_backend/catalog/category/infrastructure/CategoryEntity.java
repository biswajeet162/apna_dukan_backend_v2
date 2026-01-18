package com.apna_dukan_backend.catalog.category.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    private String id;
    
    // TODO: Add entity fields
}

