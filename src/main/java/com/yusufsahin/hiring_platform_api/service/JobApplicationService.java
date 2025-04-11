package com.yusufsahin.hiring_platform_api.service;

import com.yusufsahin.hiring_platform_api.dto.JobApplicationDto;
import com.yusufsahin.hiring_platform_api.dto.JobApplicationDtoIU;
import com.yusufsahin.hiring_platform_api.dto.JobPostingDto;
import com.yusufsahin.hiring_platform_api.dto.converter.JobApplicationDtoConverter;
import com.yusufsahin.hiring_platform_api.dto.converter.JobPostingDtoConverter;
import com.yusufsahin.hiring_platform_api.model.*;
import com.yusufsahin.hiring_platform_api.repository.JobApplicationRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final AuthService authService;
    private final JobPostingService jobPostingService;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository, AuthService authService, JobPostingService jobPostingService) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.authService = authService;
        this.jobPostingService = jobPostingService;
    }

    private JobSeeker getCurrentJobSeeker() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated.");
        }

        String userEmail = ((UserDetails) authentication.getPrincipal()).getUsername();

        User user = authService.getByEmail(userEmail)
                .orElseThrow(() -> new IllegalStateException("User not found."));

        return (JobSeeker) user;
    }

    public JobApplicationDto applyForJob(JobApplicationDtoIU request, Long jobPostingId) {

        JobApplication jobApplication = new JobApplication();
        JobSeeker jobSeeker = getCurrentJobSeeker();
        JobPosting jobPosting = JobPostingDtoConverter.toEntity(jobPostingService.getJobPostingById(jobPostingId));

        jobApplication.setCoverLetter(request.coverLetter());
        jobApplication.setJobSeeker(jobSeeker);
        jobApplication.setJobPosting(jobPosting);

        return JobApplicationDtoConverter.toDto(jobApplicationRepository.save(jobApplication));
    }

    public List<JobApplicationDto> getMyJobApplications() {

        JobSeeker jobSeeker = getCurrentJobSeeker();

        List<JobApplication> jobApplications = jobApplicationRepository.findByJobSeeker(jobSeeker);
        List<JobApplicationDto> dtoJobApplications = new ArrayList<>();

        for (JobApplication jobApplication : jobApplications) {
            dtoJobApplications.add(JobApplicationDtoConverter.toDto(jobApplication));
        }

        return dtoJobApplications;
    }

    public List<JobApplicationDto> getApplicationsForJobPosting(Long jobPostingId) throws AccessDeniedException {
        // Optional: Verify the job posting exists first
        JobPosting jobPosting = JobPostingDtoConverter.toEntity(jobPostingService.getJobPostingById(jobPostingId));

        Company currentCompany = jobPostingService.getCurrentCompany();
        if (!jobPosting.getCompany().getId().equals(currentCompany.getId())) {
            throw new AccessDeniedException("You are not authorized to view applications for this job posting.");
        }

        List<JobApplication> jobApplications = jobApplicationRepository.findByJobPosting(jobPosting);

        return jobApplications.stream()
                .map(JobApplicationDtoConverter::toDto)
                .collect(Collectors.toList());
    }


}

