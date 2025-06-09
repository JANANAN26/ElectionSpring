package com.example.Election.service;

import com.example.Election.dto.ElectionYearDTO;

import java.util.List;

public interface ElectionYearService {
    ElectionYearDTO addYear(ElectionYearDTO dto);
    List<ElectionYearDTO> getAllYears();
    ElectionYearDTO getYearById(Integer id);
}
