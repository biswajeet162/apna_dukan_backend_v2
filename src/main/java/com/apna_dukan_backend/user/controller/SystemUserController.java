package com.apna_dukan_backend.user.controller;

import com.apna_dukan_backend.auth.dto.SignupRequest;
import com.apna_dukan_backend.user.dto.user.UserProfileResponse;
import com.apna_dukan_backend.user.exception.UserNotFoundException;
import com.apna_dukan_backend.user.model.AccountStatus;
import com.apna_dukan_backend.user.model.Role;
import com.apna_dukan_backend.user.model.User;
import com.apna_dukan_backend.user.repository.UserRepository;
import com.apna_dukan_backend.user.security.UserPrincipal;
import com.apna_dukan_backend.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/system/users")
@Tag(name = "System User Management", description = "System-level API for creating and managing users")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@PreAuthorize("hasAuthority('ROLE_SYSTEM')")
public class SystemUserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @PostMapping("/create-admin")
    @Operation(summary = "Create admin user", 
               description = "Create a new admin user. Only SYSTEM role can perform this operation.")
    public ResponseEntity<UserProfileResponse> createAdminUser(
            @Valid @RequestBody SignupRequest request) {
        
        // Validate that at least email or phone is provided
        if ((request.getEmail() == null || request.getEmail().isBlank()) &&
            (request.getPhone() == null || request.getPhone().isBlank())) {
            throw new IllegalArgumentException("Either email or phone must be provided");
        }

        // Check if email already exists
        if (request.getEmail() != null && !request.getEmail().isBlank() && 
            userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Check if phone already exists
        if (request.getPhone() != null && !request.getPhone().isBlank() && 
            userRepository.existsByPhone(request.getPhone())) {
            throw new IllegalArgumentException("Phone number already exists");
        }

        User adminUser = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .emailVerified(false)
                .phoneVerified(false)
                .status(AccountStatus.ACTIVE)
                .build();

        adminUser = userRepository.save(adminUser);

        UserProfileResponse response = UserProfileResponse.builder()
                .userId(adminUser.getUserId())
                .name(adminUser.getName())
                .email(adminUser.getEmail())
                .phone(adminUser.getPhone())
                .emailVerified(adminUser.getEmailVerified())
                .phoneVerified(adminUser.getPhoneVerified())
                .role(adminUser.getRole())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/create-system")
    @Operation(summary = "Create system user", 
               description = "Create a new system user. Only SYSTEM role can perform this operation.")
    public ResponseEntity<UserProfileResponse> createSystemUser(
            @Valid @RequestBody SignupRequest request) {
        
        // Validate that at least email or phone is provided
        if ((request.getEmail() == null || request.getEmail().isBlank()) &&
            (request.getPhone() == null || request.getPhone().isBlank())) {
            throw new IllegalArgumentException("Either email or phone must be provided");
        }

        // Check if email already exists
        if (request.getEmail() != null && !request.getEmail().isBlank() && 
            userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Check if phone already exists
        if (request.getPhone() != null && !request.getPhone().isBlank() && 
            userRepository.existsByPhone(request.getPhone())) {
            throw new IllegalArgumentException("Phone number already exists");
        }

        User systemUser = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(Role.SYSTEM)
                .emailVerified(true) // System users are auto-verified
                .phoneVerified(true)
                .status(AccountStatus.ACTIVE)
                .build();

        systemUser = userRepository.save(systemUser);

        UserProfileResponse response = UserProfileResponse.builder()
                .userId(systemUser.getUserId())
                .name(systemUser.getName())
                .email(systemUser.getEmail())
                .phone(systemUser.getPhone())
                .emailVerified(systemUser.getEmailVerified())
                .phoneVerified(systemUser.getPhoneVerified())
                .role(systemUser.getRole())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{userId}/promote-to-admin")
    @Operation(summary = "Promote user to admin", 
               description = "Promote a USER to ADMIN role. Only SYSTEM role can perform this operation.")
    public ResponseEntity<UserProfileResponse> promoteToAdmin(@PathVariable UUID userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (user.getRole() == Role.ADMIN) {
            throw new IllegalArgumentException("User is already an admin");
        }

        if (user.getRole() == Role.SYSTEM) {
            throw new IllegalArgumentException("Cannot change system user role");
        }

        user.setRole(Role.ADMIN);
        user = userRepository.save(user);

        return ResponseEntity.ok(userService.getProfile(userId));
    }

    @PutMapping("/{userId}/demote-to-user")
    @Operation(summary = "Demote admin to user", 
               description = "Demote an ADMIN to USER role. Only SYSTEM role can perform this operation.")
    public ResponseEntity<UserProfileResponse> demoteToUser(@PathVariable UUID userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (user.getRole() == Role.USER) {
            throw new IllegalArgumentException("User is already a regular user");
        }

        if (user.getRole() == Role.SYSTEM) {
            throw new IllegalArgumentException("Cannot change system user role");
        }

        user.setRole(Role.USER);
        user = userRepository.save(user);

        return ResponseEntity.ok(userService.getProfile(userId));
    }
}

