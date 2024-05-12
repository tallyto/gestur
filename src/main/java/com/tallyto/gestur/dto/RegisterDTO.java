package com.tallyto.gestur.dto;

import com.tallyto.gestur.model.UserRole;
import jakarta.validation.constraints.*;

public record RegisterDTO(
        @NotNull @Email @NotEmpty
        String email,
        @NotNull @NotEmpty @Size(min = 8) String password,
        UserRole role
) {
}
