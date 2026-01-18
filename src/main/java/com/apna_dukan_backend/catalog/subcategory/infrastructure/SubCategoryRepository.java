package com.apna_dukan_backend.catalog.subcategory.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, String> {
    // TODO: Add custom query methods
}

