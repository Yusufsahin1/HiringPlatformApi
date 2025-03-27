package com.yusufsahin.hiring_platform_api.service;

import com.yusufsahin.hiring_platform_api.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthService authService;

    public UserDetailsServiceImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = authService.getByUsername(username);
        return (UserDetails) user.orElseThrow(EntityNotFoundException::new);
    }
}
