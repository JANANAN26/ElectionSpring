package com.example.Election.repositories;

import com.example.Election.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DistrictRepository extends JpaRepository<District, Integer> {
    @Query("SELECT SUM(d.districtSeat) FROM District d")
    Integer getTotalSeats();
}
