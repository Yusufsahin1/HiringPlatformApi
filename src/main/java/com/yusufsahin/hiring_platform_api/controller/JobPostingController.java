package com.yusufsahin.hiring_platform_api.controller;

import com.yusufsahin.hiring_platform_api.dto.JobPostingDto;
import com.yusufsahin.hiring_platform_api.dto.JobPostingDtoIU;
import com.yusufsahin.hiring_platform_api.service.JobPostingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/job")
@RestController
public class JobPostingController {

    private final JobPostingService jobPostingService;

    public JobPostingController(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
    }

    @PostMapping("/create")
    public JobPostingDto createJobPosting(@RequestBody JobPostingDtoIU request) {
        return jobPostingService.createJobPosting(request);
    }
}
