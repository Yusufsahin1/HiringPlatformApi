package com.yusufsahin.hiring_platform_api.repository;

import com.yusufsahin.hiring_platform_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findByEmail(String email);
}
