package com.yusufsahin.hiring_platform_api.dto;

public record JobPostingDto(
        Long id,
        String title,
        String description,
        String location,
        CompanyDto company
) {
}
