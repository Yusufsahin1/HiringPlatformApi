package com.yusufsahin.hiring_platform_api.service;

import com.yusufsahin.hiring_platform_api.dto.JobPostingDto;
import com.yusufsahin.hiring_platform_api.dto.JobPostingDtoIU;
import com.yusufsahin.hiring_platform_api.dto.converter.JobPostingDtoConverter;
import com.yusufsahin.hiring_platform_api.model.Company;
import com.yusufsahin.hiring_platform_api.model.JobPosting;
import com.yusufsahin.hiring_platform_api.model.User;
import com.yusufsahin.hiring_platform_api.repository.JobPostingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final AuthService authService;

    public JobPostingService(JobPostingRepository jobPostingRepository, AuthService authService) {
        this.jobPostingRepository = jobPostingRepository;
        this.authService = authService;
    }

    protected Company getCurrentCompany() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationCredentialsNotFoundException("User is not authenticated.");
        }

        String userEmail = ((UserDetails) authentication.getPrincipal()).getUsername();

        User user = authService.getByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Authenticated user not found in database: " + userEmail));

        return (Company) user;
    }

    public JobPostingDto createJobPosting(JobPostingDtoIU request) {

        Company company = getCurrentCompany();

        JobPosting jobPosting = new JobPosting();
        jobPosting.setTitle(request.title());
        jobPosting.setDescription(request.description());
        jobPosting.setLocation(request.location());
        jobPosting.setCompany(company);

        return JobPostingDtoConverter.toDto(jobPostingRepository.save(jobPosting));
    }

    public JobPostingDto updateJobPosting(Long id, JobPostingDtoIU request) {

        JobPosting jobPosting = jobPostingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job posting not found."));

        if (!Objects.equals(jobPosting.getCompany().getId(), getCurrentCompany().getId())) {
            throw new AccessDeniedException("You are not the owner of this job posting.");
        }

        if (request.title() != null && !request.title().isBlank())
            jobPosting.setTitle(request.title());

        if (request.description() != null && !request.description().isBlank())
            jobPosting.setDescription(request.description());

        if (request.location() != null && !request.location().isBlank())
            jobPosting.setLocation(request.location());

        return JobPostingDtoConverter.toDto(jobPostingRepository.save(jobPosting));
    }

    public void deleteJobPosting(Long id) {

        JobPosting jobPosting = jobPostingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job posting not found."));

        if (!Objects.equals(jobPosting.getCompany().getId(), getCurrentCompany().getId())) {
            throw new AccessDeniedException("You are not the owner of this job posting.");
        }

        jobPostingRepository.delete(jobPosting);
    }

    public List<JobPostingDto> getAllJobPostings() {

        List<JobPosting> jobPostings = jobPostingRepository.findAll();
        List<JobPostingDto> dtoJobPostings = new ArrayList<>();

        for (JobPosting jobPosting : jobPostings) {
            dtoJobPostings.add(JobPostingDtoConverter.toDto(jobPosting));
        }

        return dtoJobPostings;
    }

    public JobPostingDto getJobPostingById(Long id) {
        return JobPostingDtoConverter.toDto(jobPostingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job posting not found.")));
    }

    public List<JobPostingDto> getMyJobPostings() {

        Company company = getCurrentCompany();

        List<JobPosting> jobPostings = jobPostingRepository.findByCompany(company);
        List<JobPostingDto> dtoJobPostings = new ArrayList<>();

        for (JobPosting jobPosting : jobPostings) {
            dtoJobPostings.add(JobPostingDtoConverter.toDto(jobPosting));
        }

        return dtoJobPostings;
    }

    public List<JobPostingDto> getJobPostingsByCompany(Long companyId) {

        List<JobPosting> jobPostings = jobPostingRepository.findByCompanyId(companyId);
        List<JobPostingDto> dtoJobPostings = new ArrayList<>();

        for (JobPosting jobPosting : jobPostings) {
            dtoJobPostings.add(JobPostingDtoConverter.toDto(jobPosting));
        }

        return dtoJobPostings;
    }

}