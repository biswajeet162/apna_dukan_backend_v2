package com.apna_dukan_backend.cart.repository;

import com.apna_dukan_backend.cart.model.CartEntity;
import com.apna_dukan_backend.cart.model.domain.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, UUID> {
    /**
     * Find the active cart for a user.
     * There should be exactly one ACTIVE cart per user.
     */
    Optional<CartEntity> findByUserIdAndStatus(UUID userId, CartStatus status);
    
    /**
     * Find active cart for user (convenience method).
     */
    default Optional<CartEntity> findActiveCartByUserId(UUID userId) {
        return findByUserIdAndStatus(userId, CartStatus.ACTIVE);
    }
    
    /**
     * Check if user has an active cart.
     */
    boolean existsByUserIdAndStatus(UUID userId, CartStatus status);
}

