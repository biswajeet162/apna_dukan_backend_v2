package com.apna_dukan_backend.cart.repository;

import com.apna_dukan_backend.cart.model.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, UUID> {
    /**
     * Find all items in a cart.
     */
    List<CartItemEntity> findByCartId(UUID cartId);
    
    /**
     * Find a specific item in a cart by variant.
     */
    Optional<CartItemEntity> findByCartIdAndVariantId(UUID cartId, UUID variantId);
    
    /**
     * Delete all items in a cart.
     */
    @Modifying
    @Query("DELETE FROM CartItemEntity c WHERE c.cartId = :cartId")
    void deleteByCartId(@Param("cartId") UUID cartId);
    
    /**
     * Delete a specific item from a cart.
     */
    @Modifying
    @Query("DELETE FROM CartItemEntity c WHERE c.cartId = :cartId AND c.variantId = :variantId")
    void deleteByCartIdAndVariantId(@Param("cartId") UUID cartId, @Param("variantId") UUID variantId);
    
    /**
     * Count items in a cart.
     */
    long countByCartId(UUID cartId);
}

