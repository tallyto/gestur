package com.tallyto.gestur.config.database;

import com.tallyto.gestur.context.TenantContext;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver<String> {
    @Override
    public String resolveCurrentTenantIdentifier() {
        String tenant = TenantContext.getCurrentTenant();
        return Objects.requireNonNullElse(tenant, TenantContext.DEFAULT_TENANT);
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
