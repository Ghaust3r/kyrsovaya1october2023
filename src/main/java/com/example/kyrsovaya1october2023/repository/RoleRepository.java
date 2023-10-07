package com.example.kyrsovaya1october2023.repository;

import com.example.kyrsovaya1october2023.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
