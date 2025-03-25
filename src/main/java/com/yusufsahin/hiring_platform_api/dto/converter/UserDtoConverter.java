package com.yusufsahin.hiring_platform_api.dto.converter;


import com.yusufsahin.hiring_platform_api.dto.UserDto;
import com.yusufsahin.hiring_platform_api.model.User;

public class UserDtoConverter {
    public static UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getPassword(), user.getRole());
    }
}
