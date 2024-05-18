package com.tallyto.gestur.database;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfoService;
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

        // Obter informações sobre as migrações
        MigrationInfoService info = flyway.info();

        // Verificar se há migrações pendentes
        if (info.pending().length > 0) {
            // Realizar a migração somente se houver migrações pendentes
            flyway.migrate();
        }
    }
}
