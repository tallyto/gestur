package com.tallyto.gestur.repository;

import com.tallyto.gestur.enums.RoleEnum;
import com.tallyto.gestur.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByDescription(RoleEnum roleEnum);
}
