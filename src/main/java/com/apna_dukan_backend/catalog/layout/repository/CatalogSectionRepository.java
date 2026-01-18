package com.apna_dukan_backend.catalog.layout.repository;

import com.apna_dukan_backend.catalog.layout.model.CatalogSectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CatalogSectionRepository extends JpaRepository<CatalogSectionEntity, UUID> {
    List<CatalogSectionEntity> findAllByOrderByDisplayOrderAsc();
    List<CatalogSectionEntity> findByEnabledTrueOrderByDisplayOrderAsc();
}




