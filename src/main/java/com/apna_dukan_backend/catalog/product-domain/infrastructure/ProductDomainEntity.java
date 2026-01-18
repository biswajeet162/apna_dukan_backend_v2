package com.apna_dukan_backend.catalog.productdomain.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_domains")
public class ProductDomainEntity {
    @Id
    private String id;
    
    // TODO: Add entity fields
}

