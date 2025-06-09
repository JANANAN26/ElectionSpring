package com.example.Election.service.serviceimpl;

import com.example.Election.dto.PartyResultDTO;
import com.example.Election.dto.ResultResponseDTO;
import com.example.Election.entities.District;
import com.example.Election.entities.DistrictDetails;
import com.example.Election.entities.Result;
import com.example.Election.repositories.DistrictDetailsRepository;
import com.example.Election.repositories.DistrictRepository;
import com.example.Election.repositories.ResultRepository;
import com.example.Election.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private DistrictDetailsRepository districtDetailsRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Override
    public ResultResponseDTO getResultByDistrictId(Integer distId) {
        District district = districtRepository.findById(distId).orElse(null);
        DistrictDetails districtDetails = districtDetailsRepository.findByDistrict_DistId(distId);

        if (district == null || districtDetails == null) {
            return null;
        }

        int validVotes = districtDetails.getTotalVotes() - districtDetails.getRejectVotes();

        List<Result> results = resultRepository.findAllByDistrictId(distId);

        List<PartyResultDTO> partyResults = results.stream().map(result -> {
            PartyResultDTO dto = new PartyResultDTO();
            dto.setPartyName(result.getParty().getPartyName());
            dto.setPartyVotes(result.getPartyVotes());
            dto.setBonusSeats(result.getBonusSeats());
            dto.setFirstSeatAllocation(result.getFirstSeatAllocation());
            dto.setSecondSeatAllocation(result.getSecondSeatAllocation());
            dto.setFinalSeatAllocation(result.getFinalSeatAllocation());

            double percentage = validVotes > 0
                    ? (result.getPartyVotes() * 100.0) / validVotes
                    : 0.0;

            dto.setPercentage(percentage);
            dto.setStatus(result.getFinalSeatAllocation() == 0 ? "Disqualified" : "Qualified");

            return dto;
        }).collect(Collectors.toList());

        ResultResponseDTO response = new ResultResponseDTO();
        response.setDistId(district.getDistId());
        response.setDistrictName(district.getDistrictName());
        response.setTotalVotes(districtDetails.getTotalVotes());
        response.setRejectVotes(districtDetails.getRejectVotes());
        response.setValidVotes(validVotes);
        response.setPartyVotes(partyResults);

        return response;
    }
}
