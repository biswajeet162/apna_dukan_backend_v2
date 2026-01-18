package com.apna_dukan_backend.catalog.productdomain.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDomainRepository extends JpaRepository<ProductDomainEntity, String> {
    // TODO: Add custom query methods
}

