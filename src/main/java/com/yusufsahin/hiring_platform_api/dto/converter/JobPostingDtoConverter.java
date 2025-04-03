package com.yusufsahin.hiring_platform_api.dto.converter;

import com.yusufsahin.hiring_platform_api.dto.JobPostingDto;
import com.yusufsahin.hiring_platform_api.model.JobPosting;

public class JobPostingDtoConverter {
    public static JobPostingDto toDto(JobPosting jobPosting) {
        return new JobPostingDto(jobPosting.getId(), jobPosting.getTitle(), jobPosting.getDescription(), jobPosting.getLocation(),
                CompanyDtoConverter.toDto(jobPosting.getCompany()));
    }
}
