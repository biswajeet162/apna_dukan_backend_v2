package com.apna_dukan_backend.config;

import com.apna_dukan_backend.user.model.Role;
import com.apna_dukan_backend.user.model.User;
import com.apna_dukan_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer {

    private static final Logger logger = LoggerFactory.getLogger(AdminInitializer.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    public void createSuperAdminIfMissing() {
        String superAdminEmail = "boss@gmail.com";

        if (userRepository.existsByEmail(superAdminEmail)) {
            logger.info("Super admin user already exists. Skipping creation.");
            return;
        }

        try {
            User superAdmin = User.builder()
                    .firstName("Boss")
                    .lastName("Roy")
                    .email(superAdminEmail)
                    .phone("9990099900")
                    .password(passwordEncoder.encode("999000"))
                    .role(Role.SUPER_ADMIN_ROLE)
                    .enabled(true)
                    .build();

            userRepository.save(superAdmin);
            logger.info("Super admin user created successfully: {}", superAdminEmail);
        } catch (Exception e) {
            logger.error("Error creating super admin user: {}", e.getMessage(), e);
        }
    }
}

