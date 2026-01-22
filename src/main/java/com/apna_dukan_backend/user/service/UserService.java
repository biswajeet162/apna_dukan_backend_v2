package com.apna_dukan_backend.user.service;

import com.apna_dukan_backend.user.dto.user.UpdateProfileRequest;
import com.apna_dukan_backend.user.dto.user.UserProfileResponse;
import com.apna_dukan_backend.user.exception.UserNotFoundException;
import com.apna_dukan_backend.user.model.AccountStatus;
import com.apna_dukan_backend.user.model.Role;
import com.apna_dukan_backend.user.model.User;
import com.apna_dukan_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserProfileResponse getProfile(UUID userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return UserProfileResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .emailVerified(user.getEmailVerified())
                .phoneVerified(user.getPhoneVerified())
                .role(user.getRole())
                .build();
    }

    @Transactional
    public UserProfileResponse updateProfile(UUID userId, UpdateProfileRequest request) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

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
            user.setEmail(request.getEmail());
            user.setEmailVerified(false); // Require re-verification
        }

        // Update phone if provided (requires OTP verification)
        if (request.getPhone() != null && !request.getPhone().isBlank()) {
            // Check if phone is already taken by another user
            if (userRepository.existsByPhone(request.getPhone()) && 
                !request.getPhone().equals(user.getPhone())) {
                throw new IllegalArgumentException("Phone number already exists");
            }
            user.setPhone(request.getPhone());
            user.setPhoneVerified(false); // Require re-verification
        }

        // Ensure at least email or phone exists
        if ((user.getEmail() == null || user.getEmail().isBlank()) &&
            (user.getPhone() == null || user.getPhone().isBlank())) {
            throw new IllegalArgumentException("Either email or phone must be provided");
        }

        user = userRepository.save(user);

        return UserProfileResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .emailVerified(user.getEmailVerified())
                .phoneVerified(user.getPhoneVerified())
                .role(user.getRole())
                .build();
    }

    @Transactional
    public void blockUser(UUID userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setStatus(AccountStatus.BLOCKED);
        userRepository.save(user);
    }

    @Transactional
    public void unblockUser(UUID userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setStatus(AccountStatus.ACTIVE);
        userRepository.save(user);
    }

    @Transactional
    public UserProfileResponse updateUserRole(UUID userId, Role newRole, Role currentUserRole) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Validation: Only SYSTEM can promote users to ADMIN
        if (newRole == Role.ADMIN && currentUserRole != Role.SYSTEM) {
            throw new IllegalArgumentException("Only SYSTEM role can promote users to ADMIN");
        }

        // Validation: Cannot change SYSTEM user role
        if (user.getRole() == Role.SYSTEM) {
            throw new IllegalArgumentException("Cannot change SYSTEM user role");
        }

        // Validation: ADMIN can only demote to USER
        if (currentUserRole == Role.ADMIN && newRole != Role.USER) {
            throw new IllegalArgumentException("ADMIN can only change user role to USER");
        }

        user.setRole(newRole);
        user = userRepository.save(user);

        return getProfile(userId);
    }
}

