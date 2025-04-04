package com.yusufsahin.hiring_platform_api.dto;

public record AuthRequest(
        String email,
        String password
) {
}
