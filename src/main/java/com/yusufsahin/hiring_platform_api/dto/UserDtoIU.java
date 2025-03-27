package com.yusufsahin.hiring_platform_api.dto;

import com.yusufsahin.hiring_platform_api.model.UserRole;


public record UserDtoIU(

       String email,
       String password,
       UserRole role
) {
}
