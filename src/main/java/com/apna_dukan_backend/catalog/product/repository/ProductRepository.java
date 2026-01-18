package com.apna_dukan_backend.catalog.product.repository;

import com.apna_dukan_backend.catalog.product.model.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    List<ProductEntity> findByProductGroupIdAndEnabledTrueOrderByDisplayOrderAsc(UUID productGroupId);
    
    Page<ProductEntity> findByProductGroupIdAndEnabledTrueOrderByDisplayOrderAsc(UUID productGroupId, Pageable pageable);
    
    Page<ProductEntity> findByProductGroupIdOrderByDisplayOrderAsc(UUID productGroupId, Pageable pageable);
    
    Optional<ProductEntity> findByProductIdAndEnabledTrue(UUID productId);
    
    Optional<ProductEntity> findByCode(String code);
}

