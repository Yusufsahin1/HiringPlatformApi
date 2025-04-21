package com.yusufsahin.hiring_platform_api.controller;

import com.yusufsahin.hiring_platform_api.dto.JobApplicationDto;
import com.yusufsahin.hiring_platform_api.dto.JobApplicationDtoIU;
import com.yusufsahin.hiring_platform_api.service.JobApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RequestMapping("/api/v1/job-application")
@RestController
@Tag(name = "Job Applications")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @PostMapping("/apply/{jobPostingId}")
    public JobApplicationDto applyForJob(@RequestBody JobApplicationDtoIU request, @PathVariable Long jobPostingId) {
        return jobApplicationService.applyForJob(request, jobPostingId);
    }

    @GetMapping("/my-applications")
    public List<JobApplicationDto> getMyJobApplications() {
        return jobApplicationService.getMyJobApplications();
    }

    @GetMapping("/{jobPostingId}")
    public List<JobApplicationDto> getJobApplicationsByJobPosting(@PathVariable Long jobPostingId) throws AccessDeniedException {
        return jobApplicationService.getApplicationsForJobPosting(jobPostingId);
    }

}
