package com.apna_dukan_backend.user.service;

import com.apna_dukan_backend.user.exception.UserNotFoundException;
import com.apna_dukan_backend.user.model.User;
import com.apna_dukan_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VerificationService {
    private final UserRepository userRepository;

    @Transactional
    public void verifyEmail(UUID userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setEmailVerified(true);
        userRepository.save(user);
    }

    @Transactional
    public void verifyPhone(UUID userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setPhoneVerified(true);
        userRepository.save(user);
    }

    // Mock OTP generation - can be replaced with actual OTP service
    public String generateOTP(UUID userId) {
        // Mock implementation - generate 6-digit OTP
        return String.format("%06d", (int)(Math.random() * 1000000));
    }

    // Mock OTP verification - can be replaced with actual OTP service
    public boolean verifyOTP(UUID userId, String otp) {
        // Mock implementation - always return true for now
        // In production, this would check against stored OTP
        return true;
    }
}

