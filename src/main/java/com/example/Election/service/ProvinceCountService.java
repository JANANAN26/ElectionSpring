package com.company.service;

import com.example.Election.dto.ProvinceCountDTO;
import java.util.List;
import java.util.Map;

public interface ProvinceCountService {
    String updateProvinceCount(ProvinceCountDTO dto);
    Map<String, Object> getProvinceCountByYearId(Integer yearId);
    List<Map<String, Object>> getAllProvinceCounts();
}
