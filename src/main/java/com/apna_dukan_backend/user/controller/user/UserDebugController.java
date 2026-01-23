package com.apna_dukan_backend.user.controller.user;

import com.apna_dukan_backend.user.security.UserPrincipal;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Temporary debug controller to check user's role and authorities.
 * Remove this after fixing the role issue.
 */
@RestController
@RequestMapping("/api/user/debug")
public class UserDebugController {

    @GetMapping("/auth-info")
    public ResponseEntity<AuthInfo> getAuthInfo(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        AuthInfo info = new AuthInfo();
        if (userPrincipal != null) {
            info.setUserId(userPrincipal.getUserId());
            info.setUsername(userPrincipal.getUsername());
            info.setRole(userPrincipal.getRole() != null ? userPrincipal.getRole().name() : "NULL");
            info.setEnabled(userPrincipal.isEnabled());
        }
        
        if (auth != null) {
            info.setAuthenticated(auth.isAuthenticated());
            info.setAuthorities(auth.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList()));
        }
        
        return ResponseEntity.ok(info);
    }

    @Data
    @AllArgsConstructor
    static class AuthInfo {
        private java.util.UUID userId;
        private String username;
        private String role;
        private boolean enabled;
        private boolean authenticated;
        private Collection<String> authorities;
        
        public AuthInfo() {
            this.authorities = java.util.Collections.emptyList();
        }
    }
}

