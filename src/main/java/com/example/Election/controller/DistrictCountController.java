package com.example.Election.controller;

import com.example.Election.dto.DistrictCountDTO;
import com.example.Election.service.DistrictCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/districtCount")
public class DistrictCountController {

    @Autowired
    private DistrictCountService districtCountService;

    @PostMapping
    public String updateDistrictCount(@RequestBody DistrictCountDTO dto) {
        return districtCountService.updateDistrictCount(dto);
    }

    @GetMapping
    public List<DistrictCountDTO> getAllDistrictCounts() {
        return districtCountService.getAllDistrictCounts();
    }
}
