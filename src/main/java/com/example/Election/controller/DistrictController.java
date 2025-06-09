package com.example.Election.controller;

import com.example.Election.dto.DistrictDTO;
import com.example.Election.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/district")
@CrossOrigin("*")
public class DistrictController {

    @Autowired
    private DistrictService service;

    @GetMapping
    public List<DistrictDTO> getAllDistricts() {
        return service.getAllDistricts();
    }

    @PostMapping
    public String addDistrict(@RequestBody DistrictDTO dto) {
        service.addDistrict(dto);
        return "District added successfully";
    }

    @PutMapping
    public String updateDistrict(@RequestParam int distId, @RequestParam String districtName) {
        service.updateDistrictName(distId, districtName);
        return "District updated successfully";
    }
}
