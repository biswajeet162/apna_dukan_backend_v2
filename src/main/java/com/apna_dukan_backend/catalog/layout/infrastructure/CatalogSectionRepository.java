package com.apna_dukan_backend.catalog.layout.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogSectionRepository extends JpaRepository<CatalogSectionEntity, String> {
    // TODO: Add custom query methods
}

