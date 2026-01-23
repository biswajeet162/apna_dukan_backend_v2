package com.apna_dukan_backend.user.service;

import com.apna_dukan_backend.user.exception.AddressAccessDeniedException;
import com.apna_dukan_backend.user.exception.AddressNotFoundException;
import com.apna_dukan_backend.user.exception.UserNotFoundException;
import com.apna_dukan_backend.user.model.UserAddress;
import com.apna_dukan_backend.user.model.dto.address.AddressCreateRequestDto;
import com.apna_dukan_backend.user.model.dto.address.AddressResponseDto;
import com.apna_dukan_backend.user.model.dto.address.AddressUpdateRequestDto;
import com.apna_dukan_backend.user.repository.UserAddressRepository;
import com.apna_dukan_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service for managing user addresses.
 * 
 * Responsibilities:
 * - List user addresses
 * - Create address
 * - Update address
 * - Delete address
 * - Manage default address logic
 */
@Service
@RequiredArgsConstructor
public class UserAddressService {
    private final UserAddressRepository addressRepository;
    private final UserRepository userRepository;

    /**
     * Lists all addresses for a user.
     * Default address is returned first.
     * 
     * @param userId The user ID
     * @return List of AddressResponseDto, with default address first
     * @throws UserNotFoundException if user is not found
     */
    public List<AddressResponseDto> listAddresses(UUID userId) {
        // Verify user exists
        userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return addressRepository.findByUserId(userId).stream()
                .sorted(Comparator
                        .comparing((UserAddress a) -> !a.getIsDefault()) // Default addresses first
                        .thenComparing(UserAddress::getCreatedAt, Comparator.reverseOrder())) // Newest first
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Creates a new address for the user.
     * 
     * Rules:
     * - If isDefault = true, unset previous default address
     * - Pincode format is validated (6 digits)
     * 
     * @param userId The user ID
     * @param request The create request
     * @return Created AddressResponseDto
     * @throws UserNotFoundException if user is not found
     * @throws IllegalArgumentException if validation fails
     */
    @Transactional
    public AddressResponseDto createAddress(UUID userId, AddressCreateRequestDto request) {
        // Verify user exists
        userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Validate pincode format (already validated by @Pattern, but double-check)
        if (request.getPincode() == null || !request.getPincode().matches("^[0-9]{6}$")) {
            throw new IllegalArgumentException("Pincode must be exactly 6 digits");
        }

        // If this is set as default, unset other default addresses
        if (Boolean.TRUE.equals(request.getIsDefault())) {
            addressRepository.unsetDefaultAddresses(userId);
        }

        UserAddress address = UserAddress.builder()
                .userId(userId)
                .name(request.getName())
                .phone(request.getPhone())
                .line1(request.getLine1())
                .line2(request.getLine2())
                .city(request.getCity())
                .state(request.getState())
                .pincode(request.getPincode())
                .country(request.getCountry() != null ? request.getCountry() : "India")
                .type(request.getType())
                .isDefault(Boolean.TRUE.equals(request.getIsDefault()))
                .build();

        address = addressRepository.save(address);

        return mapToResponse(address);
    }

    /**
     * Updates an existing address.
     * 
     * Rules:
     * - Address must belong to the user
     * - If isDefault = true, unset previous default address
     * - userId and addressId cannot be changed
     * 
     * @param userId The user ID
     * @param addressId The address ID
     * @param request The update request
     * @return Updated AddressResponseDto
     * @throws AddressNotFoundException if address is not found
     * @throws AddressAccessDeniedException if address does not belong to user
     * @throws IllegalArgumentException if validation fails
     */
    @Transactional
    public AddressResponseDto updateAddress(UUID userId, UUID addressId, AddressUpdateRequestDto request) {
        // Verify user owns this address
        UserAddress address = addressRepository.findByAddressIdAndUserId(addressId, userId)
                .orElseThrow(() -> {
                    // Check if address exists but doesn't belong to user
                    if (addressRepository.findById(addressId).isPresent()) {
                        throw new AddressAccessDeniedException("Address does not belong to user");
                    }
                    throw new AddressNotFoundException("Address not found");
                });

        // Validate pincode if provided
        if (request.getPincode() != null && !request.getPincode().matches("^[0-9]{6}$")) {
            throw new IllegalArgumentException("Pincode must be exactly 6 digits");
        }

        // If this is set as default, unset other default addresses
        if (Boolean.TRUE.equals(request.getIsDefault())) {
            addressRepository.unsetDefaultAddresses(userId);
        }

        // Update fields if provided
        if (request.getName() != null && !request.getName().isBlank()) {
            address.setName(request.getName());
        }
        if (request.getPhone() != null && !request.getPhone().isBlank()) {
            address.setPhone(request.getPhone());
        }
        if (request.getLine1() != null && !request.getLine1().isBlank()) {
            address.setLine1(request.getLine1());
        }
        if (request.getLine2() != null) {
            address.setLine2(request.getLine2());
        }
        if (request.getCity() != null && !request.getCity().isBlank()) {
            address.setCity(request.getCity());
        }
        if (request.getState() != null && !request.getState().isBlank()) {
            address.setState(request.getState());
        }
        if (request.getPincode() != null && !request.getPincode().isBlank()) {
            address.setPincode(request.getPincode());
        }
        if (request.getCountry() != null && !request.getCountry().isBlank()) {
            address.setCountry(request.getCountry());
        }
        if (request.getType() != null) {
            address.setType(request.getType());
        }
        if (request.getIsDefault() != null) {
            address.setIsDefault(request.getIsDefault());
        }

        address = addressRepository.save(address);

        return mapToResponse(address);
    }

    /**
     * Deletes an address.
     * 
     * Rules:
     * - Address must belong to the user
     * - If deleted address was default, auto-select another address as default (if exists)
     * 
     * @param userId The user ID
     * @param addressId The address ID
     * @throws AddressNotFoundException if address is not found
     * @throws AddressAccessDeniedException if address does not belong to user
     */
    @Transactional
    public void deleteAddress(UUID userId, UUID addressId) {
        // Verify user owns this address
        UserAddress address = addressRepository.findByAddressIdAndUserId(addressId, userId)
                .orElseThrow(() -> {
                    // Check if address exists but doesn't belong to user
                    if (addressRepository.findById(addressId).isPresent()) {
                        throw new AddressAccessDeniedException("Address does not belong to user");
                    }
                    throw new AddressNotFoundException("Address not found");
                });

        boolean wasDefault = Boolean.TRUE.equals(address.getIsDefault());

        addressRepository.delete(address);

        // If deleted address was default, set another address as default (if exists)
        if (wasDefault) {
            List<UserAddress> remainingAddresses = addressRepository.findByUserId(userId);
            if (!remainingAddresses.isEmpty()) {
                // Set the first remaining address as default
                UserAddress newDefault = remainingAddresses.get(0);
                newDefault.setIsDefault(true);
                addressRepository.save(newDefault);
            }
        }
    }

    /**
     * Maps UserAddress entity to AddressResponseDto.
     */
    private AddressResponseDto mapToResponse(UserAddress address) {
        return AddressResponseDto.builder()
                .addressId(address.getAddressId())
                .name(address.getName())
                .phone(address.getPhone())
                .line1(address.getLine1())
                .line2(address.getLine2())
                .city(address.getCity())
                .state(address.getState())
                .pincode(address.getPincode())
                .country(address.getCountry())
                .type(address.getType())
                .isDefault(address.getIsDefault())
                .createdAt(address.getCreatedAt())
                .build();
    }
}

