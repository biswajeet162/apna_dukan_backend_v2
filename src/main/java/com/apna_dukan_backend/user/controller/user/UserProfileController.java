package com.apna_dukan_backend.user.controller.user;

import com.apna_dukan_backend.user.model.dto.profile.UserProfileResponseDto;
import com.apna_dukan_backend.user.model.dto.profile.UserProfileUpdateRequestDto;
import com.apna_dukan_backend.user.security.UserPrincipal;
import com.apna_dukan_backend.user.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for user profile management.
 * 
 * All endpoints require authentication with ROLE_USER.
 * userId is derived from JWT SecurityContext, never from request parameters.
 */
@RestController
@RequestMapping("/api/user/profile")
@Tag(name = "User Profile", description = "API for user profile management")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserProfileController {
    private final UserProfileService userProfileService;

    /**
     * GET /api/user/profile
     * 
     * Get the authenticated user's profile.
     * Returns only identity-level data (no addresses, orders, cart, etc.).
     */
    @GetMapping
    @Operation(summary = "Get user profile", description = "Get the authenticated user's profile")
    public ResponseEntity<UserProfileResponseDto> getProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        UserProfileResponseDto response = userProfileService.fetchUserProfile(userPrincipal.getUserId());
        return ResponseEntity.ok(response);
    }

    /**
     * PUT /api/user/profile
     * 
     * Update the authenticated user's profile.
     * 
     * Rules:
     * - If email is updated, emailVerified is reset to false
     * - If phone is updated, phoneVerified is reset to false
     */
    @PutMapping
    @Operation(summary = "Update user profile", description = "Update the authenticated user's profile")
    public ResponseEntity<UserProfileResponseDto> updateProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @Valid @RequestBody UserProfileUpdateRequestDto request) {
        UserProfileResponseDto response = userProfileService.updateUserProfile(
                userPrincipal.getUserId(), request);
        return ResponseEntity.ok(response);
    }
}

