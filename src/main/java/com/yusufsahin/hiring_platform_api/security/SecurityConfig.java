package com.yusufsahin.hiring_platform_api.security;

import com.yusufsahin.hiring_platform_api.model.UserRole;
import com.yusufsahin.hiring_platform_api.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter, JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x ->
                        x
                                .requestMatchers("/api/v1/auth/**").permitAll()

                                .requestMatchers("/api/v1/job/create").hasAuthority(UserRole.COMPANY.name())
                                .requestMatchers("/api/v1/job/update/**").hasAuthority(UserRole.COMPANY.name())
                                .requestMatchers("/api/v1/job/delete/**").hasAuthority(UserRole.COMPANY.name())
                                .requestMatchers("/api/v1/job/my-postings").hasAuthority(UserRole.COMPANY.name())
                                .requestMatchers("/api/v1/job").permitAll()
                                .requestMatchers("/api/v1/job/{id}").permitAll()
                                .requestMatchers("/api/v1/job/company/**").permitAll()

                                .requestMatchers("/api/v1/job-application/apply/**").hasAuthority(UserRole.JOB_SEEKER.name())
                                .requestMatchers("/api/v1/job-application/my-applications").hasAuthority(UserRole.JOB_SEEKER.name())
                                .requestMatchers("/api/v1/job-application/{jobPostingId}").hasAuthority(UserRole.COMPANY.name())
                                .requestMatchers("/api/v1/job-application/**").authenticated()

                                //.anyRequest().authenticated()
                                .anyRequest().permitAll()
                )
                .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
