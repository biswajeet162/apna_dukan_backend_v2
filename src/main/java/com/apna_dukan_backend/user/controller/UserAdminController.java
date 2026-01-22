package com.apna_dukan_backend.user.controller;

import com.apna_dukan_backend.user.dto.UpdateRoleRequest;
import com.apna_dukan_backend.user.dto.user.UserProfileResponse;
import com.apna_dukan_backend.user.security.UserPrincipal;
import com.apna_dukan_backend.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/users")
@Tag(name = "User Admin", description = "Admin API for managing user roles")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserAdminController {

    private final UserService userService;

    @PutMapping("/{userId}/role")
    @PreAuthorize("hasAnyAuthority('ROLE_SYSTEM', 'ROLE_ADMIN')")
    @Operation(summary = "Update user role", 
               description = "Update a user's role. SYSTEM can promote users to ADMIN. " +
                           "ADMIN can only demote users to USER. Only SYSTEM can promote to ADMIN.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User role updated successfully",
                    content = @Content(schema = @Schema(implementation = UserProfileResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "403", description = "Access denied - SYSTEM or ADMIN role required"),
            @ApiResponse(responseCode = "400", description = "Invalid role assignment")
    })
    public ResponseEntity<UserProfileResponse> updateUserRole(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable UUID userId,
            @Valid @RequestBody UpdateRoleRequest request) {
        
        UserProfileResponse response = userService.updateUserRole(
                userId, 
                request.getRole(), 
                userPrincipal.getRole()
        );

        return ResponseEntity.ok(response);
    }
}
