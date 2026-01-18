package com.apna_dukan_backend.inventory.repository;

import com.apna_dukan_backend.inventory.model.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, UUID> {
    Optional<InventoryEntity> findByVariantIdAndWarehouseId(UUID variantId, String warehouseId);
    
    List<InventoryEntity> findByVariantId(UUID variantId);
    
    @Query("SELECT i FROM InventoryEntity i WHERE i.variantId IN :variantIds")
    List<InventoryEntity> findByVariantIdIn(@Param("variantIds") List<UUID> variantIds);
}

