package com.example.Election.service.serviceimpl;

import com.example.Election.dto.ProvinceCountDTO;
import com.example.Election.entities.ElectionYear;
import com.example.Election.repositories.ElectionYearRepository;
import com.company.service.ProvinceCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProvinceCountServiceImpl implements ProvinceCountService {

    @Autowired
    private ElectionYearRepository yearRepo;

    @Override
    public String updateProvinceCount(ProvinceCountDTO dto) {
        ElectionYear year = yearRepo.findById(dto.getYearId()).orElse(null);
        if (year == null) return "Election year not found";

        year.setProvinceCount(dto.getProvinceCount());
        year.setNowProvinceCount(dto.getProvinceCount());
        yearRepo.save(year);

        return "Province count updated successfully";
    }

    @Override
    public Map<String, Object> getProvinceCountByYearId(Integer yearId) {
        ElectionYear year = yearRepo.findById(yearId).orElse(null);
        if (year == null) return null;

        Map<String, Object> map = new HashMap<>();
        map.put("electionYear", year.getElectionYear());
        map.put("provinceCount", year.getProvinceCount());
        return map;
    }

    @Override
    public List<Map<String, Object>> getAllProvinceCounts() {
        List<ElectionYear> years = yearRepo.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        for (ElectionYear year : years) {
            Map<String, Object> map = new HashMap<>();
            map.put("yearId", year.getYearId());
            map.put("electionYear", year.getElectionYear());
            map.put("provinceCount", year.getProvinceCount());
            result.add(map);
        }

        return result;
    }
}
