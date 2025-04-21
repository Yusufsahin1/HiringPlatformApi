package com.yusufsahin.hiring_platform_api.controller;

import com.yusufsahin.hiring_platform_api.dto.JobPostingDto;
import com.yusufsahin.hiring_platform_api.dto.JobPostingDtoIU;
import com.yusufsahin.hiring_platform_api.service.JobPostingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/job")
@RestController
@Tag(name = "Job Postings")
public class JobPostingController {

    private final JobPostingService jobPostingService;

    public JobPostingController(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
    }

    @PostMapping("/create")
    public JobPostingDto createJobPosting(@Valid @RequestBody JobPostingDtoIU request) {
        return jobPostingService.createJobPosting(request);
    }

    @PutMapping("/update/{id}")
    public JobPostingDto updateJobPosting(@RequestBody JobPostingDtoIU request, @PathVariable Long id) {
        return jobPostingService.updateJobPosting(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteJobPosting(@PathVariable Long id) {
        jobPostingService.deleteJobPosting(id);
    }

    @GetMapping
    public List<JobPostingDto> getAllJobPostings() {
        return jobPostingService.getAllJobPostings();
    }

    @GetMapping("/{id}")
    public JobPostingDto getJobPostingById(@PathVariable Long id) {
        return jobPostingService.getJobPostingById(id);
    }

    @GetMapping("/my-postings")
    public List<JobPostingDto> getMyJobPostings() {
        return jobPostingService.getMyJobPostings();
    }

    @GetMapping("/company/{companyId}")
    public List<JobPostingDto> getJobPostingsByCompany(@PathVariable Long companyId) {
        return jobPostingService.getJobPostingsByCompany(companyId);

    }


}
