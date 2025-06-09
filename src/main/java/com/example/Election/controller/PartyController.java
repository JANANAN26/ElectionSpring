package com.example.Election.controller;

import com.example.Election.dto.PartyDTO;
import com.example.Election.entities.Party;
import com.example.Election.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/party")
@CrossOrigin(origins = "http://localhost:5173")  // Same CORS config as before
public class PartyController {

    @Autowired
    private PartyService partyService;

    @PostMapping
    public Party createParty(@RequestBody PartyDTO dto) {
        return partyService.createParty(dto);
    }

    @PutMapping("/{id}")
    public Party updateParty(@PathVariable int id, @RequestBody PartyDTO dto) {
        return partyService.updateParty(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteParty(@PathVariable int id) {
        partyService.deleteParty(id);
    }

    @GetMapping
    public List<Party> getAllParties() {
        return partyService.getAllParties();
    }
}
