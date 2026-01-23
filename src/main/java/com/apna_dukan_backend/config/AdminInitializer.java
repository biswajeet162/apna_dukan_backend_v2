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
    public void createSystemUsersIfMissing() {
        // Create SYSTEM user
        createSystemUser();
        // Create ADMIN user
        createAdminUser();
        // Create regular USER
        createRegularUser();
        // Fix users without role (set to USER by default)
        fixUsersWithoutRole();
    }
    
    private void fixUsersWithoutRole() {
        try {
            long count = userRepository.findAll().stream()
                    .filter(user -> user.getRole() == null)
                    .peek(user -> {
                        user.setRole(Role.USER);
                        userRepository.save(user);
                        logger.info("Fixed user role for: {} - set to USER", user.getEmail());
                    })
                    .count();
            
            if (count > 0) {
                logger.info("Fixed {} users without role - set to USER", count);
            }
        } catch (Exception e) {
            logger.error("Error fixing users without role: {}", e.getMessage(), e);
        }
    }

    private void createSystemUser() {
        String systemEmail = "system@gmail.com";

        if (userRepository.existsByEmail(systemEmail)) {
            logger.info("System user already exists. Skipping creation.");
            return;
        }

        try {
            User systemUser = User.builder()
                    .name("System Administrator")
                    .email(systemEmail)
                    .phone("0000000000")
                    .passwordHash(passwordEncoder.encode("System@123")) // Change this in production!
                    .role(Role.SYSTEM)
                    .emailVerified(true)
                    .phoneVerified(true)
                    .status(com.apna_dukan_backend.user.model.AccountStatus.ACTIVE)
                    .build();

            userRepository.save(systemUser);
            logger.info("System user created successfully: {} (Password: System@123)", systemEmail);
        } catch (Exception e) {
            logger.error("Error creating system user: {}", e.getMessage(), e);
        }
    }

    private void createAdminUser() {
        String adminEmail = "admin@gmail.com";

        if (userRepository.existsByEmail(adminEmail)) {
            logger.info("Admin user already exists. Skipping creation.");
            return;
        }

        try {
            User adminUser = User.builder()
                    .name("Admin User")
                    .email(adminEmail)
                    .phone("9990099900")
                    .passwordHash(passwordEncoder.encode("Admin@123")) // Change this in production!
                    .role(Role.ADMIN)
                    .emailVerified(true)
                    .phoneVerified(true)
                    .status(com.apna_dukan_backend.user.model.AccountStatus.ACTIVE)
                    .build();

            userRepository.save(adminUser);
            logger.info("Admin user created successfully: {} (Password: Admin@123)", adminEmail);
        } catch (Exception e) {
            logger.error("Error creating admin user: {}", e.getMessage(), e);
        }
    }

    private void createRegularUser() {
        String userEmail = "user@gmail.com";

        if (userRepository.existsByEmail(userEmail)) {
            logger.info("Regular user already exists. Skipping creation.");
            return;
        }

        try {
            User regularUser = User.builder()
                    .name("Regular User")
                    .email(userEmail)
                    .phone("8880088800")
                    .passwordHash(passwordEncoder.encode("User@123"))
                    .role(Role.USER)
                    .emailVerified(true)
                    .phoneVerified(true)
                    .status(com.apna_dukan_backend.user.model.AccountStatus.ACTIVE)
                    .build();

            userRepository.save(regularUser);
            logger.info("Regular user created successfully: {} (Password: user@123)", userEmail);
        } catch (Exception e) {
            logger.error("Error creating regular user: {}", e.getMessage(), e);
        }
    }
}

