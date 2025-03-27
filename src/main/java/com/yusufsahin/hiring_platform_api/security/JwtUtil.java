package com.yusufsahin.hiring_platform_api.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${jwt.key}")
    private String SECRET;
}
