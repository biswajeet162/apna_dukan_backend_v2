package com.apna_dukan_backend.catalog.category.repository;

import com.apna_dukan_backend.catalog.category.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    List<CategoryEntity> findBySectionIdOrderByDisplayOrderAsc(UUID sectionId);
    List<CategoryEntity> findBySectionIdAndEnabledTrueOrderByDisplayOrderAsc(UUID sectionId);
}

