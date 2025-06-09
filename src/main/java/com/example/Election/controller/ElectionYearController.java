package com.example.Election.controller;

import com.example.Election.dto.ApiResponse;
import com.example.Election.dto.ElectionYearDTO;
import com.example.Election.service.ElectionYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/years")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ElectionYearController {

    private final ElectionYearService service;

    @PostMapping
    public ResponseEntity<ApiResponse<ElectionYearDTO>> addYear(@RequestBody ElectionYearDTO dto) {
        try {
            var saved = service.addYear(dto);
            return ResponseEntity.status(201)
                    .body(new ApiResponse<>(true, "Election year added", saved));
        } catch (RuntimeException e) {
            return ResponseEntity.status(409)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ElectionYearDTO>>> getAllYears() {
        List<ElectionYearDTO> list = service.getAllYears();
        return ResponseEntity.ok(new ApiResponse<>(true, "All years retrieved", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ElectionYearDTO>> getYearById(@PathVariable Integer id) {
        try {
            var dto = service.getYearById(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Year found", dto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }
}
