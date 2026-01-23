package com.apna_dukan_backend.user.service;

import com.apna_dukan_backend.user.exception.UserNotFoundException;
import com.apna_dukan_backend.user.model.User;
import com.apna_dukan_backend.user.model.dto.profile.UserProfileResponseDto;
import com.apna_dukan_backend.user.model.dto.profile.UserProfileUpdateRequestDto;
import com.apna_dukan_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Service for managing user profile (identity-level data).
 * 
 * Responsibilities:
 * - Fetch user profile
 * - Update user profile
 * 
 * Does NOT handle:
 * - Authentication (handled by AuthService)
 * - Addresses (handled by UserAddressService)
 * - Orders, Cart, Reviews, etc.
 */
@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserRepository userRepository;

    /**
     * Fetches the user profile for the given userId.
     * 
     * @param userId The user ID
     * @return UserProfileResponseDto containing identity-level data
     * @throws UserNotFoundException if user is not found
     */
    public UserProfileResponseDto fetchUserProfile(UUID userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return UserProfileResponseDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .emailVerified(user.getEmailVerified())
                .phoneVerified(user.getPhoneVerified())
                .role(user.getRole())
                .build();
    }

    /**
     * Updates the user profile.
     * 
     * Rules:
     * - If email is updated, emailVerified is reset to false
     * - If phone is updated, phoneVerified is reset to false
     * - At least email or phone must exist
     * 
     * @param userId The user ID
     * @param request The update request
     * @return Updated UserProfileResponseDto
     * @throws UserNotFoundException if user is not found
     * @throws IllegalArgumentException if email/phone already exists or validation fails
     */
    @Transactional
    public UserProfileResponseDto updateUserProfile(UUID userId, UserProfileUpdateRequestDto request) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Track if email/phone changed to reset verification flags
        boolean emailChanged = false;
        boolean phoneChanged = false;

        // Update name if provided
        if (request.getName() != null && !request.getName().isBlank()) {
            user.setName(request.getName());
        }

        // Update email if provided (requires re-verification)
        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            // Check if email is already taken by another user
            if (userRepository.existsByEmail(request.getEmail()) && 
                !request.getEmail().equals(user.getEmail())) {
                throw new IllegalArgumentException("Email already exists");
            }
            emailChanged = !request.getEmail().equals(user.getEmail());
            user.setEmail(request.getEmail());
            if (emailChanged) {
                user.setEmailVerified(false); // Require re-verification
            }
        }

        // Update phone if provided (requires re-verification)
        if (request.getPhone() != null && !request.getPhone().isBlank()) {
            // Check if phone is already taken by another user
            if (userRepository.existsByPhone(request.getPhone()) && 
                !request.getPhone().equals(user.getPhone())) {
                throw new IllegalArgumentException("Phone number already exists");
            }
            phoneChanged = !request.getPhone().equals(user.getPhone());
            user.setPhone(request.getPhone());
            if (phoneChanged) {
                user.setPhoneVerified(false); // Require re-verification
            }
        }

        // Ensure at least email or phone exists
        if ((user.getEmail() == null || user.getEmail().isBlank()) &&
            (user.getPhone() == null || user.getPhone().isBlank())) {
            throw new IllegalArgumentException("Either email or phone must be provided");
        }

        user = userRepository.save(user);

        return UserProfileResponseDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .emailVerified(user.getEmailVerified())
                .phoneVerified(user.getPhoneVerified())
                .role(user.getRole())
                .build();
    }
}

