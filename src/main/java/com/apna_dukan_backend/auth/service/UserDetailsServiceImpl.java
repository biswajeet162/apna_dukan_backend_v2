package com.apna_dukan_backend.auth.service;

import com.apna_dukan_backend.user.model.User;
import com.apna_dukan_backend.user.repository.UserRepository;
import com.apna_dukan_backend.user.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Try to find by email first, then by phone
        User user = userRepository.findByEmail(username)
                .orElseGet(() -> userRepository.findByPhone(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username)));
        
        return UserPrincipal.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .password(user.getPasswordHash())
                .role(user.getRole())
                .enabled(user.getStatus() == com.apna_dukan_backend.user.model.AccountStatus.ACTIVE)
                .build();
    }

    public UserDetails loadUserByUserId(UUID userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));
        
        return UserPrincipal.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .password(user.getPasswordHash())
                .role(user.getRole())
                .enabled(user.getStatus() == com.apna_dukan_backend.user.model.AccountStatus.ACTIVE)
                .build();
    }
}

