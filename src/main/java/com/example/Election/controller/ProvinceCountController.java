package com.example.Election.controller;

import com.example.Election.dto.ProvinceCountDTO;
import com.company.service.ProvinceCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/provinceCount")
@CrossOrigin
public class ProvinceCountController {

    @Autowired
    private ProvinceCountService service;

    @PostMapping
    public String update(@RequestBody ProvinceCountDTO dto) {
        return service.updateProvinceCount(dto);
    }

    @GetMapping("/{yearId}")
    public Map<String, Object> get(@PathVariable Integer yearId) {
        return service.getProvinceCountByYearId(yearId);
    }

    @GetMapping
    public List<Map<String, Object>> getAll() {
        return service.getAllProvinceCounts();
    }
}
