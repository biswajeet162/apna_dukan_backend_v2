package com.apna_dukan_backend.user.repository;

import com.apna_dukan_backend.user.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for UserAddress entities.
 * 
 * This repository follows the naming convention: UserAddressRepository
 * (as per the requirements structure).
 */
@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, UUID> {
    /**
     * Find all addresses for a user.
     */
    List<UserAddress> findByUserId(UUID userId);

    /**
     * Find an address by ID that belongs to a specific user.
     */
    Optional<UserAddress> findByAddressIdAndUserId(UUID addressId, UUID userId);

    /**
     * Unset all default addresses for a user.
     */
    @Modifying
    @Query("UPDATE UserAddress a SET a.isDefault = false WHERE a.userId = :userId AND a.isDefault = true")
    void unsetDefaultAddresses(@Param("userId") UUID userId);

    /**
     * Find the default address for a user.
     */
    Optional<UserAddress> findByUserIdAndIsDefaultTrue(UUID userId);
}

