package com.yusufsahin.hiring_platform_api.repository;

import com.yusufsahin.hiring_platform_api.model.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
}
