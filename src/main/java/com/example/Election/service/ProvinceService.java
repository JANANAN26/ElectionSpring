package com.example.Election.service;

import com.example.Election.dto.ProvinceDTO;
import java.util.List;

public interface ProvinceService {
    String addProvince(ProvinceDTO dto);
    String updateProvince(ProvinceDTO dto);
    ProvinceDTO getProvinceById(Integer id);
    List<ProvinceDTO> getAllProvinces();
    void updateDistrictCount(int provinceId, int districtCount);
}
