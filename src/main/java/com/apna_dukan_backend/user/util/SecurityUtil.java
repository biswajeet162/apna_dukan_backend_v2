package com.apna_dukan_backend.user.util;

import com.apna_dukan_backend.user.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

/**
 * Utility class for extracting user information from SecurityContext.
 * 
 * This class provides helper methods to get the authenticated user's
 * userId and role from the JWT-authenticated SecurityContext.
 */
public class SecurityUtil {

    /**
     * Gets the authenticated user's ID from SecurityContext.
     * 
     * @return UUID of the authenticated user
     * @throws IllegalStateException if user is not authenticated or userId cannot be extracted
     */
    public static UUID getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated");
        }
        
        Object principal = authentication.getPrincipal();
        
        if (principal instanceof UserPrincipal) {
            return ((UserPrincipal) principal).getUserId();
        }
        
        if (principal instanceof UserDetails) {
            // Fallback: try to extract from UserDetails if it's a custom implementation
            throw new IllegalStateException("Unable to extract userId from authentication principal");
        }
        
        throw new IllegalStateException("Unexpected authentication principal type");
    }

    /**
     * Gets the authenticated user's role from SecurityContext.
     * 
     * @return Role of the authenticated user
     * @throws IllegalStateException if user is not authenticated or role cannot be extracted
     */
    public static com.apna_dukan_backend.user.model.Role getCurrentUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated");
        }
        
        Object principal = authentication.getPrincipal();
        
        if (principal instanceof UserPrincipal) {
            return ((UserPrincipal) principal).getRole();
        }
        
        throw new IllegalStateException("Unable to extract role from authentication principal");
    }

    /**
     * Gets the UserPrincipal from SecurityContext.
     * 
     * @return UserPrincipal of the authenticated user
     * @throws IllegalStateException if user is not authenticated
     */
    public static UserPrincipal getCurrentUserPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated");
        }
        
        Object principal = authentication.getPrincipal();
        
        if (principal instanceof UserPrincipal) {
            return (UserPrincipal) principal;
        }
        
        throw new IllegalStateException("Unexpected authentication principal type");
    }
}

