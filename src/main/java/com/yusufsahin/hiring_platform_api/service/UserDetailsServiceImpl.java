package com.yusufsahin.hiring_platform_api.service;

import com.yusufsahin.hiring_platform_api.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthService authService;

    public UserDetailsServiceImpl(@Lazy AuthService authService) {
        this.authService = authService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = authService.getByEmail(email);
        return (UserDetails) user.orElseThrow(EntityNotFoundException::new);
    }
}
