package com.example.Election.service;

import com.example.Election.dto.DistrictDTO;

import java.util.List;

public interface DistrictService {
    List<DistrictDTO> getAllDistricts();
    void addDistrict(DistrictDTO dto);
    void updateDistrictName(int distId, String districtName);
}
