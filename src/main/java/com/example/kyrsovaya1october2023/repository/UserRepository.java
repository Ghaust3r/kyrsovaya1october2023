package com.example.kyrsovaya1october2023.repository;

import com.example.kyrsovaya1october2023.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}