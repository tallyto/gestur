package com.tallyto.gestur.model;

import com.tallyto.gestur.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@Setter
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private RoleEnum description;
    @Override
    public String getAuthority() {
        return "ROLE_" + description.toString();
    }
}
