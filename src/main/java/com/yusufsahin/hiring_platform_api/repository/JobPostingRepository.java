package com.yusufsahin.hiring_platform_api.repository;

import com.yusufsahin.hiring_platform_api.model.Company;
import com.yusufsahin.hiring_platform_api.model.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {

    List<JobPosting> findByCompany(Company company);
    List<JobPosting> findByCompanyId(Long companyId);
}
