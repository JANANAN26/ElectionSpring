package com.example.Election.service;

import com.example.Election.dto.DistrictCountDTO;
import java.util.List;

public interface DistrictCountService {
    String updateDistrictCount(DistrictCountDTO dto);
    List<DistrictCountDTO> getAllDistrictCounts();
}
