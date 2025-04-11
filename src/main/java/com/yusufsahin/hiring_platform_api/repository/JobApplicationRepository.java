package com.yusufsahin.hiring_platform_api.repository;

import com.yusufsahin.hiring_platform_api.model.JobApplication;
import com.yusufsahin.hiring_platform_api.model.JobPosting;
import com.yusufsahin.hiring_platform_api.model.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByJobSeeker(JobSeeker jobSeeker);

    List<JobApplication> findByJobPosting(JobPosting jobPosting);
}
