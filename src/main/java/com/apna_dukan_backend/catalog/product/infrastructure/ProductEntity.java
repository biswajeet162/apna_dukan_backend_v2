package com.apna_dukan_backend.catalog.product.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    private String id;
    
    // TODO: Add entity fields
}

