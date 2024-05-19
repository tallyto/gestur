package com.tallyto.gestur.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record TenantDTO(
        @NotBlank String domain,
        @NotBlank String name,
        @NotBlank @Email String email,
        @Size(max = 15) String phoneNumber,
        String address
) {
}
