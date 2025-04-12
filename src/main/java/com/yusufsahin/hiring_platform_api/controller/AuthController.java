package com.yusufsahin.hiring_platform_api.controller;


import com.yusufsahin.hiring_platform_api.dto.*;
import com.yusufsahin.hiring_platform_api.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register-company")
    public CompanyDto registerCompany(@Valid @RequestBody CompanyDtoIU request) {
        return authService.registerCompany(request);
    }

    @PostMapping("/register-jobseeker")
    public JobSeekerDto registerJobSeeker(@Valid @RequestBody JobSeekerDtoIU request) {
        return authService.registerJobSeeker(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }

}
