package com.apna_dukan_backend.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

@Component
public class DataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private DataSource dataSource;

    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeData() {
        // Only run in production profile
        if (!"prod".equals(activeProfile)) {
            return;
        }

        try {
            // Check if catalog_section table exists (created by Hibernate)
            if (!tableExists("catalog_section")) {
                logger.error("Table catalog_section does not exist. Hibernate should have created it. Cannot initialize data.");
                return;
            }

            // Check if data already exists
            if (hasData("catalog_section")) {
                logger.info("Database already initialized with data. Skipping data.sql execution.");
                return;
            }

            logger.info("Initializing database with data.sql...");
            
            // Load and execute data.sql
            ClassPathResource resource = new ClassPathResource("data.sql");
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.setScripts(resource);
            populator.setContinueOnError(true); // Continue even if some inserts fail (idempotency)
            
            try (Connection connection = dataSource.getConnection()) {
                populator.populate(connection);
                logger.info("Successfully initialized database with data.sql");
            }
        } catch (Exception e) {
            logger.error("Error initializing database data: {}", e.getMessage(), e);
            // Don't fail startup - data might already exist
        }
    }

    private boolean tableExists(String tableName) {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, tableName.toUpperCase(), null);
            boolean exists = tables.next();
            tables.close();
            return exists;
        } catch (Exception e) {
            logger.warn("Error checking if table exists: {}", e.getMessage());
            return false;
        }
    }

    private boolean hasData(String tableName) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT COUNT(*) FROM " + tableName.toUpperCase();
            try (var stmt = connection.createStatement();
                 var rs = stmt.executeQuery(sql)) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
            return false;
        } catch (Exception e) {
            logger.warn("Error checking if table has data: {}", e.getMessage());
            return false;
        }
    }
}

