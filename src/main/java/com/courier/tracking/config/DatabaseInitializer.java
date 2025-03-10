package com.courier.tracking.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseInitializer {
    private final DataSource dataSource;

    @PostConstruct
    public void initializeDatabase() {
        executeSqlScript("schema.sql");
    }

    private void executeSqlScript(String scriptPath) {
        Resource resource = new ClassPathResource(scriptPath);
        try (Connection connection = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(connection, new EncodedResource(resource, StandardCharsets.UTF_8));
            log.info("[INFO] {} executed successfully.", scriptPath);
        } catch (SQLException e) {
            log.error("[ERROR] Failed to execute {}: {}", scriptPath, e.getMessage());
        }
    }
}
