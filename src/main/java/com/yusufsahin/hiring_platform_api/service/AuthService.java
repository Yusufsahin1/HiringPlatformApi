package com.yusufsahin.hiring_platform_api.service;

import com.yusufsahin.hiring_platform_api.dto.*;
import com.yusufsahin.hiring_platform_api.dto.converter.CompanyDtoConverter;
import com.yusufsahin.hiring_platform_api.dto.converter.JobSeekerDtoConverter;
import com.yusufsahin.hiring_platform_api.model.Company;
import com.yusufsahin.hiring_platform_api.model.JobSeeker;
import com.yusufsahin.hiring_platform_api.model.User;
import com.yusufsahin.hiring_platform_api.repository.UserRepository;
import com.yusufsahin.hiring_platform_api.security.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Slf4j
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil, AuthenticationManager authenticationManager, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> getByEmail(String email) { // Dto döndürecek şekilde düzenlenecek
        return userRepository.findByEmail(email);
    }

    @Transactional
    public CompanyDto registerCompany(CompanyDtoIU request) {

        Company newCompany = new Company();
        newCompany.setEmail(request.email());
        newCompany.setPassword(passwordEncoder.encode(request.password()));
        newCompany.setRole(request.role()); // COMPANY
        newCompany.setCompanyName(request.companyName());

        return CompanyDtoConverter.toDto(userRepository.save(newCompany));
    }

    public JobSeekerDto registerJobSeeker(JobSeekerDtoIU request) {

        JobSeeker newJobSeeker = new JobSeeker();
        newJobSeeker.setEmail(request.email());
        newJobSeeker.setPassword(passwordEncoder.encode(request.password()));
        newJobSeeker.setRole(request.role()); // JOB_SEEKER
        newJobSeeker.setName(request.name());
        newJobSeeker.setSurname(request.surname());
        newJobSeeker.setResumeUrl(request.resumeUrl());

        return JobSeekerDtoConverter.toDto(userRepository.save(newJobSeeker));
    }

    public String login(@RequestBody UserDto request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(),
                request.password()));

        if (authentication.isAuthenticated()) {
            return jwtUtil.generateToken(request.email());
        }

        log.info("invalid email {}", request.email());
        throw new UsernameNotFoundException("invalid email {} " + request.email());
    }
}
