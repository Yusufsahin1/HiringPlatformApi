package com.yusufsahin.hiring_platform_api.dto;

import com.yusufsahin.hiring_platform_api.model.UserRole;

public record CompanyDto(
        Long id,
        String email,
        UserRole role,
        String companyName
) {
}
