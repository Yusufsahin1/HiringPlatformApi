package com.yusufsahin.hiring_platform_api.dto;

import com.yusufsahin.hiring_platform_api.model.UserRole;

public record JobSeekerDtoIU(
        String email,
        String password,
        UserRole role,
        String name,
        String surname,
        String resumeUrl
) {
}
