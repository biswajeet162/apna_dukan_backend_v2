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

@Repository
public interface AddressRepository extends JpaRepository<UserAddress, UUID> {
    List<UserAddress> findByUserId(UUID userId);

    Optional<UserAddress> findByAddressIdAndUserId(UUID addressId, UUID userId);

    @Modifying
    @Query("UPDATE UserAddress a SET a.isDefault = false WHERE a.userId = :userId AND a.isDefault = true")
    void unsetDefaultAddresses(@Param("userId") UUID userId);

    Optional<UserAddress> findByUserIdAndIsDefaultTrue(UUID userId);
}

