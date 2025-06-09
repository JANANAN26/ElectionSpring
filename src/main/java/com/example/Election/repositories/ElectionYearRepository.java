package com.example.Election.repositories;

import com.example.Election.entities.ElectionYear;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ElectionYearRepository extends JpaRepository<ElectionYear, Integer> {
    Optional<ElectionYear> findByElectionYear(Integer electionYear);
}
