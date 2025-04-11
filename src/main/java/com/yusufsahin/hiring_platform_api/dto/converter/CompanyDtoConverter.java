package com.yusufsahin.hiring_platform_api.dto.converter;

import com.yusufsahin.hiring_platform_api.dto.CompanyDto;
import com.yusufsahin.hiring_platform_api.model.Company;

public class CompanyDtoConverter {
    public static CompanyDto toDto(Company company) {
        return new CompanyDto(company.getId(), company.getEmail(), company.getRole(), company.getCompanyName());
    }

    public static Company toEntity(CompanyDto companyDto) {
        Company company = new Company();
        company.setId(companyDto.id());
        company.setEmail(companyDto.email());
        company.setRole(companyDto.role());
        company.setCompanyName(companyDto.companyName());
        return company;
    }

}
