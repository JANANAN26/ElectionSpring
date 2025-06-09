package com.example.Election.service;

import com.example.Election.dto.PartyStatsDTO;
import com.example.Election.dto.TotalVotesDTO;

// SriLankaService.java
public interface SriLankaService {
    TotalVotesDTO getTotalStats();
    PartyStatsDTO getPartyStats(int partyId);
}
