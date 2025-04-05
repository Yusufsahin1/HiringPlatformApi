package com.yusufsahin.hiring_platform_api.service;

import com.yusufsahin.hiring_platform_api.dto.JobPostingDto;
import com.yusufsahin.hiring_platform_api.dto.JobPostingDtoIU;
import com.yusufsahin.hiring_platform_api.dto.converter.JobPostingDtoConverter;
import com.yusufsahin.hiring_platform_api.model.Company;
import com.yusufsahin.hiring_platform_api.model.JobPosting;
import com.yusufsahin.hiring_platform_api.model.User;
import com.yusufsahin.hiring_platform_api.repository.JobPostingRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final AuthService authService;

    public JobPostingService(JobPostingRepository jobPostingRepository, AuthService authService) {
        this.jobPostingRepository = jobPostingRepository;
        this.authService = authService;
    }

    private Company getCurrentCompany() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated.");
        }

        String userEmail = ((UserDetails) authentication.getPrincipal()).getUsername();

        User user = authService.getByEmail(userEmail)
                .orElseThrow(() -> new IllegalStateException("User not found."));

        return (Company) user;
    }

    @Transactional
    public JobPostingDto createJobPosting(JobPostingDtoIU request) {
        Company company = getCurrentCompany();

        JobPosting jobPosting = new JobPosting();
        jobPosting.setTitle(request.title());
        jobPosting.setDescription(request.description());
        jobPosting.setLocation(request.location());
        jobPosting.setCompany(company);

        return JobPostingDtoConverter.toDto(jobPostingRepository.save(jobPosting));
    }
}

