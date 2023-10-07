package com.example.kyrsovaya1october2023.repository;

import com.example.kyrsovaya1october2023.entity.Automobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomobileRepository extends JpaRepository<Automobile, Long> {
}
