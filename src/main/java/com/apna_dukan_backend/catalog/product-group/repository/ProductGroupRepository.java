package com.apna_dukan_backend.catalog.productgroup.repository;

import com.apna_dukan_backend.catalog.productgroup.model.ProductGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductGroupRepository extends JpaRepository<ProductGroupEntity, UUID> {
    List<ProductGroupEntity> findBySubCategoryIdOrderByDisplayOrderAsc(UUID subCategoryId);
    List<ProductGroupEntity> findBySubCategoryIdAndEnabledTrueOrderByDisplayOrderAsc(UUID subCategoryId);
}


