package com.apna_dukan_backend.user.service;

import com.apna_dukan_backend.user.dto.address.AddressRequest;
import com.apna_dukan_backend.user.dto.address.AddressResponse;
import com.apna_dukan_backend.user.exception.UserNotFoundException;
import com.apna_dukan_backend.user.model.UserAddress;
import com.apna_dukan_backend.user.repository.AddressRepository;
import com.apna_dukan_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public List<AddressResponse> getAddresses(UUID userId) {
        // Verify user exists
        userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return addressRepository.findByUserId(userId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public AddressResponse addAddress(UUID userId, AddressRequest request) {
        // Verify user exists
        userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

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

    @Transactional
    public AddressResponse updateAddress(UUID userId, UUID addressId, AddressRequest request) {
        // Verify user owns this address
        UserAddress address = addressRepository.findByAddressIdAndUserId(addressId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Address not found or access denied"));

        // If this is set as default, unset other default addresses
        if (Boolean.TRUE.equals(request.getIsDefault())) {
            addressRepository.unsetDefaultAddresses(userId);
        }

        address.setName(request.getName());
        address.setPhone(request.getPhone());
        address.setLine1(request.getLine1());
        address.setLine2(request.getLine2());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setPincode(request.getPincode());
        if (request.getCountry() != null) {
            address.setCountry(request.getCountry());
        }
        address.setType(request.getType());
        address.setIsDefault(Boolean.TRUE.equals(request.getIsDefault()));

        address = addressRepository.save(address);

        return mapToResponse(address);
    }

    @Transactional
    public void deleteAddress(UUID userId, UUID addressId) {
        // Verify user owns this address
        UserAddress address = addressRepository.findByAddressIdAndUserId(addressId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Address not found or access denied"));

        addressRepository.delete(address);
    }

    private AddressResponse mapToResponse(UserAddress address) {
        return AddressResponse.builder()
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

