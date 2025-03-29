package com.yusufsahin.hiring_platform_api.controller;


import com.yusufsahin.hiring_platform_api.dto.UserDto;
import com.yusufsahin.hiring_platform_api.dto.UserDtoIU;
import com.yusufsahin.hiring_platform_api.model.User;
import com.yusufsahin.hiring_platform_api.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public User register(@RequestBody UserDtoIU request) {
        return authService.Register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDto request) {
        return authService.Login(request);
    }
}
