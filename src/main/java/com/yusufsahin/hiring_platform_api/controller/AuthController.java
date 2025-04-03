package com.yusufsahin.hiring_platform_api.controller;


import com.yusufsahin.hiring_platform_api.dto.*;
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

    @PostMapping("/register-company")
    public CompanyDto registerCompany(@RequestBody CompanyDtoIU request) {
        return authService.registerCompany(request);
    }

    @PostMapping("/register-jobseeker")
    public JobSeekerDto registerJobSeeker(@RequestBody JobSeekerDtoIU request) {
        return authService.registerJobSeeker(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDto request) {
        return authService.login(request);
    }
}
