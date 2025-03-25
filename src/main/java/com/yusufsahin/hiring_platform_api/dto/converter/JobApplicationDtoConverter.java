package com.yusufsahin.hiring_platform_api.dto.converter;

import com.yusufsahin.hiring_platform_api.dto.JobApplicationDto;
import com.yusufsahin.hiring_platform_api.model.JobApplication;

public class JobApplicationDtoConverter {
    public static JobApplicationDto toDto(JobApplication jobApplication) {
        return new JobApplicationDto(jobApplication.getId(), jobApplication.getCoverLetter(),
                JobSeekerDtoConverter.toDto(jobApplication.getJobSeeker()), JobPostingDtoConverter.toDto(jobApplication.getJobPosting()));
    }
}
