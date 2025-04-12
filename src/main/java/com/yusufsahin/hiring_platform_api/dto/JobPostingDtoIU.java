package com.yusufsahin.hiring_platform_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record JobPostingDtoIU(

        @NotBlank
        @Size(min = 8, max = 40)
        String title,

        @NotBlank
        String description,

        @NotBlank
        String location
) {
}
