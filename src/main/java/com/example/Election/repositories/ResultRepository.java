package com.example.Election.repositories;


import com.example.Election.entities.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Integer> {
    @Query("SELECT SUM(r.partyVotes) FROM Result r WHERE r.party.partyId = :partyId")
    Integer getPartyTotalVotes(@Param("partyId") int partyId);

    @Query("SELECT SUM(r.finalSeatAllocation) FROM Result r WHERE r.party.partyId = :partyId")
    Integer getPartyTotalSeats(@Param("partyId") int partyId);

    @Query("SELECT SUM(r.partyVotes) FROM Result r WHERE r.party.partyId = :partyId")
    Integer getPartyVotesForPercentage(@Param("partyId") int partyId);

    @Query("SELECT SUM(r.partyVotes) FROM Result r")
    Integer getTotalVotesAllParties();

    @Query("SELECT r FROM Result r WHERE r.district.distId = :distId")
    List<Result> findAllByDistrictId(Integer distId);

}

