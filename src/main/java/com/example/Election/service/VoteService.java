package com.example.Election.service;

import com.example.Election.dto.PartyVoteDTO;
import com.example.Election.dto.VoteSubmissionDTO;
import com.example.Election.entities.District;
import com.example.Election.entities.DistrictDetails;
import com.example.Election.entities.Party;
import com.example.Election.entities.Result;
import com.example.Election.repositories.DistrictDetailsRepository;
import com.example.Election.repositories.DistrictRepository;
import com.example.Election.repositories.PartyRepository;
import com.example.Election.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class VoteService {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private DistrictDetailsRepository districtDetailsRepository;

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Transactional
    public List<Result> saveOrUpdateVotes(VoteSubmissionDTO dto) {
        District district = districtRepository.findById(dto.getDistrictId())
                .orElseThrow(() -> new RuntimeException("District not found"));

        // Save or update DistrictDetails
        DistrictDetails details = districtDetailsRepository.findByDistrict_DistId(dto.getDistrictId());
        if (details == null) {
            details = new DistrictDetails();
            details.setDistrict(district);
        }
        details.setTotalVotes(dto.getTotalVotes());
        details.setRejectVotes(dto.getRejectVotes());
        districtDetailsRepository.save(details);

        // Remove old results for district (for update scenario)
        resultRepository.deleteByDistrict_DistId(dto.getDistrictId());

        // Save new results with votes
        List<Result> results = new ArrayList<>();
        for (PartyVoteDTO pv : dto.getPartyVotes()) {
            Party party = partyRepository.findById(pv.getPartyId())
                    .orElseThrow(() -> new RuntimeException("Party not found"));

            Result result = new Result();
            result.setDistrict(district);
            result.setParty(party);
            result.setPartyVotes(pv.getVotes());
            result.setBonusSeats(0);
            result.setFirstSeatAllocation(0);
            result.setSecondSeatAllocation(0);
            result.setFinalSeatAllocation(0);

            results.add(resultRepository.save(result));
        }

        // Perform seat allocation logic here
        calculateSeats(results, district.getDistrictSeat());

        // Save updated results
        resultRepository.saveAll(results);

        return results;
    }

    private void calculateSeats(List<Result> results, int totalSeats) {
        // Step 1: Determine qualified parties (votes > 5%)
        int totalValidVotes = results.stream().mapToInt(Result::getPartyVotes).sum();

        List<Result> qualifiedResults = new ArrayList<>();
        List<Result> disqualifiedResults = new ArrayList<>();

        for (Result r : results) {
            double votePercent = (r.getPartyVotes() * 100.0) / totalValidVotes;
            if (votePercent > 5) {
                qualifiedResults.add(r);
            } else {
                disqualifiedResults.add(r);
            }
        }

        // Calculate total qualified votes
        int totalQualifiedVotes = qualifiedResults.stream().mapToInt(Result::getPartyVotes).sum();

        // Calculate vote per seat
        double votePerSeat = (double) totalQualifiedVotes / totalSeats;

        // Find party with max votes (for bonus seat)
        Result maxVoteResult = null;
        for (Result r : qualifiedResults) {
            if (maxVoteResult == null || r.getPartyVotes() > maxVoteResult.getPartyVotes()) {
                maxVoteResult = r;
            }
        }

        // Reset all seat counts first
        for (Result r : results) {
            r.setBonusSeats(0);
            r.setFirstSeatAllocation(0);
            r.setSecondSeatAllocation(0);
            r.setFinalSeatAllocation(0);
        }

        // Allocate bonus seat to max vote party if exists
        if (maxVoteResult != null) {
            maxVoteResult.setBonusSeats(1);
        }

        // Step 2: First seat allocation (trunc division)
        Map<Result, Double> remainders = new HashMap<>();
        int seatsAllocated = 0;
        for (Result r : qualifiedResults) {
            int firstSeats = (int) (r.getPartyVotes() / votePerSeat);
            r.setFirstSeatAllocation(firstSeats);
            seatsAllocated += firstSeats;

            double remainder = (r.getPartyVotes() / votePerSeat) - firstSeats;
            remainders.put(r, remainder);
        }

        // Add bonus seat to total seats allocated
        if (maxVoteResult != null) {
            seatsAllocated += 1;
        }

        // Step 3: Allocate remaining seats based on highest remainders
        int remainingSeats = totalSeats - seatsAllocated;

        while (remainingSeats > 0) {
            Result maxRemainderResult = null;
            double maxRemainder = -1;
            for (Map.Entry<Result, Double> entry : remainders.entrySet()) {
                if (entry.getValue() > maxRemainder) {
                    maxRemainder = entry.getValue();
                    maxRemainderResult = entry.getKey();
                }
            }
            if (maxRemainderResult == null) {
                break;
            }
            maxRemainderResult.setSecondSeatAllocation(maxRemainderResult.getSecondSeatAllocation() + 1);
            remainders.put(maxRemainderResult, 0.0);
            remainingSeats--;
        }

        // Step 4: Calculate final seat allocation per party
        for (Result r : results) {
            int finalSeats = r.getBonusSeats() + r.getFirstSeatAllocation() + r.getSecondSeatAllocation();
            r.setFinalSeatAllocation(finalSeats);
        }
    }
}
