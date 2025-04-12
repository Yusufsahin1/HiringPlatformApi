package com.yusufsahin.hiring_platform_api.dto;

import com.yusufsahin.hiring_platform_api.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CompanyDtoIU(

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min=7, max=16)
        String password,

        @NotNull
        UserRole role,

        @NotBlank
        String companyName
) {
}
