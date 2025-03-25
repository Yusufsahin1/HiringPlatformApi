package com.yusufsahin.hiring_platform_api.dto;

public record JobApplicationDto(
        Long id,
        String coverLetter,
        JobSeekerDto jobSeeker,
        JobPostingDto jobPosting
) {
}
