package com.example.Election.repositories;

import com.example.Election.entities.District;
import com.example.Election.entities.DistrictDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DistrictDetailsRepository extends JpaRepository<DistrictDetails, Integer> {
    Optional<DistrictDetails> findByDistrict(District district);
    @Query("SELECT SUM(d.totalVotes) FROM DistrictDetails d")
    Integer getTotalVotes();

    @Query("SELECT SUM(d.totalVotes - d.rejectVotes) FROM DistrictDetails d")
    Integer getTotalValidVotes();

    @Query("SELECT SUM(d.rejectVotes) FROM DistrictDetails d")
    Integer getTotalRejectVotes();

    DistrictDetails findByDistrict_DistId(Integer distId);

}
