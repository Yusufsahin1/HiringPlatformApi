package com.yusufsahin.hiring_platform_api.dto;

public record JobSeekerDto(
        Long id,
        String name,
        String surname,
        String resumeUrl,
        UserDto user
) {
}
