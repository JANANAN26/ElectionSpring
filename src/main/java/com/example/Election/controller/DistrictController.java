package com.example.Election.controller;

import com.example.Election.dto.ApiResponse;
import com.example.Election.dto.DistrictDTO;
import com.example.Election.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/district")
@CrossOrigin("*")
public class DistrictController {

    @Autowired
    private DistrictService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<DistrictDTO>>> getAllDistricts() {
        List<DistrictDTO> districts = service.getAllDistricts();
        return ResponseEntity.ok(new ApiResponse<>(true, "Districts fetched successfully", districts));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> addDistrict(@RequestBody DistrictDTO dto) {
        service.addDistrict(dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "District added successfully", null));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Void>> updateDistrict(@RequestParam int distId, @RequestParam String districtName) {
        service.updateDistrictName(distId, districtName);
        return ResponseEntity.ok(new ApiResponse<>(true, "District updated successfully", null));
    }
}
