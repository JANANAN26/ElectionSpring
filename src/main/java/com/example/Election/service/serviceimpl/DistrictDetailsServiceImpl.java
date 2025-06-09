package com.example.Election.service.serviceimpl;

import com.example.Election.dto.DistrictDetailsDTO;
import com.example.Election.entities.District;
import com.example.Election.entities.DistrictDetails;
import com.example.Election.repositories.DistrictDetailsRepository;
import com.example.Election.repositories.DistrictRepository;
import com.example.Election.service.DistrictDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistrictDetailsServiceImpl implements DistrictDetailsService {

    @Autowired
    private DistrictDetailsRepository districtDetailsRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public DistrictDetailsDTO addDistrictDetails(DistrictDetailsDTO dto) {
        // Fetch the District entity
        District district = districtRepository.findById(dto.getDistId())
                .orElseThrow(() -> new RuntimeException("District not found with ID: " + dto.getDistId()));

        // Create and populate DistrictDetails entity
        DistrictDetails entity = new DistrictDetails();
        entity.setDistrict(district);
        entity.setTotalVotes(dto.getTotalVotes());
        entity.setRejectVotes(dto.getRejectVotes());

        // Save entity
        districtDetailsRepository.save(entity);

        // Calculate valid votes
        dto.setValidVotes(dto.getTotalVotes() - dto.getRejectVotes());
        return dto;
    }

    @Override
    public DistrictDetailsDTO updateDistrictDetails(DistrictDetailsDTO dto) {
        // Fetch existing DistrictDetails entity using districtdetailsId
        DistrictDetails entity = districtDetailsRepository.findById(dto.getDistrictdetailsId())
                .orElseThrow(() -> new RuntimeException("DistrictDetails not found with ID: " + dto.getDistrictdetailsId()));

        entity.setTotalVotes(dto.getTotalVotes());
        entity.setRejectVotes(dto.getRejectVotes());

        districtDetailsRepository.save(entity);

        dto.setValidVotes(dto.getTotalVotes() - dto.getRejectVotes());
        return dto;
    }

    @Override
    public DistrictDetailsDTO getDetailsByDistrictId(Integer distId) {
        District district = districtRepository.findById(distId)
                .orElseThrow(() -> new RuntimeException("District not found"));

        DistrictDetails entity = districtDetailsRepository.findByDistrict(district)
                .orElseThrow(() -> new RuntimeException("DistrictDetails not found"));

        int validVotes = entity.getTotalVotes() - entity.getRejectVotes();

        return new DistrictDetailsDTO(
                entity.getDistrictdetailsId(),
                distId,
                entity.getTotalVotes(),
                entity.getRejectVotes(),
                validVotes
        );
    }


}
