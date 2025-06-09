package com.example.Election.service;

import com.example.Election.dto.DistrictDetailsDTO;

public interface DistrictDetailsService {
    DistrictDetailsDTO addDistrictDetails(DistrictDetailsDTO dto);
    DistrictDetailsDTO updateDistrictDetails(DistrictDetailsDTO dto);
    DistrictDetailsDTO getDetailsByDistrictId(Integer distId); // <- required
}

