package com.example.Election.service.serviceimpl;

import com.example.Election.dto.PartyStatsDTO;
import com.example.Election.dto.TotalVotesDTO;
import com.example.Election.repositories.DistrictDetailsRepository;
import com.example.Election.repositories.DistrictRepository;
import com.example.Election.repositories.ResultRepository;
import com.example.Election.service.SriLankaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// SriLankaServiceImpl.java
@Service
public class SriLankaServiceImpl implements SriLankaService {

    @Autowired
    private DistrictDetailsRepository districtDetailsRepo;
    @Autowired private DistrictRepository districtRepo;
    @Autowired private ResultRepository resultRepo;

    @Override
    public TotalVotesDTO getTotalStats() {
        TotalVotesDTO dto = new TotalVotesDTO();
        dto.setTotalVotes(districtDetailsRepo.getTotalVotes());
        dto.setTotalValidVotes(districtDetailsRepo.getTotalValidVotes());
        dto.setTotalRejectVotes(districtDetailsRepo.getTotalRejectVotes());
        dto.setTotalSeats(districtRepo.getTotalSeats());
        return dto;
    }

    @Override
    public PartyStatsDTO getPartyStats(int partyId) {
        Integer totalVotes = resultRepo.getPartyTotalVotes(partyId);
        Integer totalSeats = resultRepo.getPartyTotalSeats(partyId);
        Integer partyVotes = resultRepo.getPartyVotesForPercentage(partyId);
        Integer totalValidVotes = districtDetailsRepo.getTotalValidVotes();

        double percentage = (totalValidVotes != null && totalValidVotes > 0) ?
                ((double) partyVotes / totalValidVotes) * 100 : 0;

        PartyStatsDTO dto = new PartyStatsDTO();
        dto.setPartyId(partyId);
        dto.setTotalVotes(totalVotes != null ? totalVotes : 0);
        dto.setTotalSeats(totalSeats != null ? totalSeats : 0);
        dto.setPercentage(percentage);

        return dto;
    }
}

