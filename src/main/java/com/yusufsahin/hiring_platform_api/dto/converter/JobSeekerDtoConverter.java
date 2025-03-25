package com.yusufsahin.hiring_platform_api.dto.converter;

import com.yusufsahin.hiring_platform_api.dto.JobSeekerDto;
import com.yusufsahin.hiring_platform_api.model.JobSeeker;

public class JobSeekerDtoConverter {
    public static JobSeekerDto toDto(JobSeeker jobSeeker) {
        return new JobSeekerDto(jobSeeker.getId(), jobSeeker.getName(), jobSeeker.getSurname(), jobSeeker.getResumeUrl(), UserDtoConverter.toDto(jobSeeker.getUser()));
    }
}
