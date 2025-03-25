package com.yusufsahin.hiring_platform_api.dto;

public record CompanyDto(
        Long id,
        String companyName,
        UserDto user
) {
}
