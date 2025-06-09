package com.example.Election.controller;

import com.example.Election.dto.ResultResponseDTO;
import com.example.Election.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @GetMapping("/{distId}")
    public ResponseEntity<?> getResultsByDistrict(@PathVariable Integer distId) {
        ResultResponseDTO result = resultService.getResultByDistrictId(distId);
        if (result == null) {
            return ResponseEntity.status(404).body("{\"error\": \"District not found or no result data available.\"}");
        }
        return ResponseEntity.ok(result);
    }
}
