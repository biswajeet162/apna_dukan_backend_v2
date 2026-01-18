package com.apna_dukan_backend.catalog.variant.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantRepository extends JpaRepository<VariantEntity, String> {
    // TODO: Add custom query methods
}

