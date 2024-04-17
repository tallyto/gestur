package com.tallyto.gestur.database;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class FlywayMigrationService {
    private final DataSource dataSource;

    @Autowired
    public FlywayMigrationService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void migrateTenantSchema(String schemaName) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .schemas(schemaName)
                .load();
        flyway.migrate();
    }
}
