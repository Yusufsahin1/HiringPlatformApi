package com.yusufsahin.hiring_platform_api.dto;

import com.yusufsahin.hiring_platform_api.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record JobSeekerDtoIU(

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 7, max = 16)
        String password,

        @NotNull
        UserRole role,

        @NotBlank
        String name,

        @NotBlank
        String surname,

        @URL
        String resumeUrl
) {
}
