package com.example.Election.service.serviceimpl;

import com.example.Election.dto.DistrictCountDTO;
import com.example.Election.entities.Province;
import com.example.Election.repositories.ProvinceRepository;
import com.example.Election.service.DistrictCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class DistrictCountServiceImpl implements DistrictCountService {

    @Autowired
    private ProvinceRepository provinceRepo;

    @Override
    public String updateDistrictCount(DistrictCountDTO dto) {
        Province province = provinceRepo.findById(dto.getProvinceId()).orElse(null);
        if (province == null) return "Province not found";

        province.setDistrictCount(dto.getDistrictCount());
        province.setNowDistrictCount(dto.getDistrictCount());
        provinceRepo.save(province);

        return "District count updated successfully";
    }

    @Override
    public List<DistrictCountDTO> getAllDistrictCounts() {
        return provinceRepo.findAll().stream().map(p -> {
            DistrictCountDTO dto = new DistrictCountDTO();
            dto.setProvinceId(p.getProvinceId());
            dto.setDistrictCount(p.getDistrictCount());
            return dto;
        }).collect(Collectors.toList());
    }
}
