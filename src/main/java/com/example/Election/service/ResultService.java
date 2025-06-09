package com.example.Election.service;

import com.example.Election.dto.ProvinceDTO;
import com.example.Election.dto.ResultResponseDTO;

import java.util.List;

public interface ResultService {
    ResultResponseDTO getResultByDistrictId(Integer distId);
}
