package com.yusufsahin.hiring_platform_api.dto;

import com.yusufsahin.hiring_platform_api.model.UserRole;

public record JobSeekerDto(
        Long id,
        String email,
        UserRole role,
        String name,
        String surname,
        String resumeUrl
) {
}
