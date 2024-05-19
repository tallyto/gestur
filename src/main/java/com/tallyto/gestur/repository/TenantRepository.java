package com.tallyto.gestur.repository;

import com.tallyto.gestur.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {
    Tenant getTenantByDomain(String domain);
}