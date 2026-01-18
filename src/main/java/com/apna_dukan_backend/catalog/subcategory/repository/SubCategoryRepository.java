package com.apna_dukan_backend.catalog.subcategory.repository;

import com.apna_dukan_backend.catalog.subcategory.model.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, UUID> {
    List<SubCategoryEntity> findByCategoryIdOrderByDisplayOrderAsc(UUID categoryId);
    List<SubCategoryEntity> findByCategoryIdAndEnabledTrueOrderByDisplayOrderAsc(UUID categoryId);
}


