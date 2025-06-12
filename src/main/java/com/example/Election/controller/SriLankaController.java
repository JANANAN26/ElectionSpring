package com.example.Election.controller;

import com.example.Election.dto.PartyStatsDTO;
import com.example.Election.dto.TotalVotesDTO;
import com.example.Election.service.SriLankaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/srilanka")
@CrossOrigin(origins = "http://localhost:5174")
public class SriLankaController {

    @Autowired
    private SriLankaService sriLankaService;

    @GetMapping("/total")
    public ResponseEntity<TotalVotesDTO> getTotalStats() {
        return ResponseEntity.ok(sriLankaService.getTotalStats());
    }

    @GetMapping("/party/{partyId}")
    public ResponseEntity<PartyStatsDTO> getPartyStats(@PathVariable int partyId) {
        return ResponseEntity.ok(sriLankaService.getPartyStats(partyId));
    }
}

