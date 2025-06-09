package com.example.Election.controller;

import com.example.Election.dto.DistrictDetailsDTO;
import com.example.Election.service.DistrictDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/district-details")
public class DistrictDetailsController {

    @Autowired
    private DistrictDetailsService service;

    @PostMapping
    public DistrictDetailsDTO addDistrictDetails(@RequestBody DistrictDetailsDTO dto) {
        return service.addDistrictDetails(dto);
    }

    @PutMapping
    public DistrictDetailsDTO updateDistrictDetails(@RequestBody DistrictDetailsDTO dto) {
        return service.updateDistrictDetails(dto);
    }

    @GetMapping("/{distId}")
    public DistrictDetailsDTO getDistrictDetailsByDistrictId(@PathVariable Integer distId) {
        return service.getDetailsByDistrictId(distId);
    }
}
