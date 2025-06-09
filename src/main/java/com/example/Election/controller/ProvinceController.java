package com.example.Election.controller;

import com.example.Election.dto.ProvinceDTO;
import com.example.Election.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/province")
@CrossOrigin
public class ProvinceController {

    @Autowired
    private ProvinceService service;

    @PostMapping
    public String add(@RequestBody ProvinceDTO dto) {
        return service.addProvince(dto);
    }

    @PutMapping
    public String update(@RequestBody ProvinceDTO dto) {
        return service.updateProvince(dto);
    }

    @GetMapping("/{id}")
    public ProvinceDTO get(@PathVariable Integer id) {
        return service.getProvinceById(id);
    }

    @GetMapping
    public List<ProvinceDTO> getAll() {
        return service.getAllProvinces();
    }

    @GetMapping("/districtCount")
    public List<ProvinceDTO> getDistrictCounts() {
        return service.getAllProvinces();
    }

    @PostMapping("/districtCount")
    public String updateDistrictCount(@RequestParam int provinceId, @RequestParam int districtCount) {
        service.updateDistrictCount(provinceId, districtCount);
        return "District count updated successfully";
    }
}
