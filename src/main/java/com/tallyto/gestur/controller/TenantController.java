package com.tallyto.gestur.controller;

import com.tallyto.gestur.dto.TenantDTO;
import com.tallyto.gestur.exception.ResourceNotFoundException;
import com.tallyto.gestur.model.Tenant;
import com.tallyto.gestur.repository.TenantRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {

    @Autowired
    private TenantRepository tenantRepository;

    @GetMapping
    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tenant> getTenantById(@PathVariable Long id) {
        Tenant tenant = tenantRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tenant not found with id " + id));
        return ResponseEntity.ok(tenant);
    }

    @PostMapping
    public Tenant createTenant(@Valid @RequestBody TenantDTO tenantDTO) {
        Tenant tenant = new Tenant();
        tenant.setDomain(tenantDTO.domain());
        tenant.setName(tenantDTO.name());
        tenant.setEmail(tenantDTO.email());
        tenant.setPhoneNumber(tenantDTO.phoneNumber());
        tenant.setAddress(tenantDTO.address());
        return tenantRepository.save(tenant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tenant> updateTenant(@PathVariable Long id, @Valid @RequestBody TenantDTO tenantDTO) {
        Tenant tenant = tenantRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tenant not found with id " + id));
        tenant.setDomain(tenantDTO.domain());
        tenant.setName(tenantDTO.name());
        tenant.setEmail(tenantDTO.email());
        tenant.setPhoneNumber(tenantDTO.phoneNumber());
        tenant.setAddress(tenantDTO.address());
        final Tenant updatedTenant = tenantRepository.save(tenant);
        return ResponseEntity.ok(updatedTenant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTenant(@PathVariable Long id) {
        Tenant tenant = tenantRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tenant not found with id " + id));
        tenantRepository.delete(tenant);
        return ResponseEntity.noContent().build();
    }
}