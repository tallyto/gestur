package com.tallyto.gestur.filter;

import com.tallyto.gestur.context.TenantContext;
import com.tallyto.gestur.database.FlywayMigrationService;
import com.tallyto.gestur.repository.TenantRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.tallyto.gestur.context.TenantContext.PRIVATE_TENANT_HEADER;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
@Slf4j
public class TenantFilter extends OncePerRequestFilter {

    @Autowired
    private FlywayMigrationService flywayMigrationService;

    @Autowired
    private TenantRepository tenantRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String privateTenant = request.getHeader(PRIVATE_TENANT_HEADER);

        if (privateTenant != null && !privateTenant.isEmpty()) {
            var tenant = tenantRepository.getTenantByDomain(privateTenant);
            if (tenant == null) {
                log.error("Tenant not found for domain: " + privateTenant);
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Tenant not found");
                return;
            }
            TenantContext.setCurrentTenant(privateTenant);
            flywayMigrationService.migrateTenantSchema(privateTenant);
        }

        filterChain.doFilter(request, response);
    }
}