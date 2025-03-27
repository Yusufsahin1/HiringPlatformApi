package com.yusufsahin.hiring_platform_api.service;

import com.yusufsahin.hiring_platform_api.dto.UserDto;
import com.yusufsahin.hiring_platform_api.dto.UserDtoIU;
import com.yusufsahin.hiring_platform_api.model.User;
import com.yusufsahin.hiring_platform_api.repository.UserRepository;

import java.util.Optional;

public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getByUsername(String username) { // Dto döndürecek şekilde düzenlenecek
        return userRepository.findByUsername(username);
    }

    public User Register(UserDtoIU request) {

        User newUser = new User();
        newUser.setEmail(request.email());
        newUser.setPassword(request.password()); // passwordEncoder.encode(request.password())
        newUser.setRole(request.role());

        return userRepository.save(newUser);
    }
}
