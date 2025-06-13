package com.example.Election.controller;

import com.example.Election.dto.VoteSubmissionDTO;
import com.example.Election.entities.Result;
import com.example.Election.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping("/submit")
    public ResponseEntity<List<Result>> submitVotes(@RequestBody VoteSubmissionDTO dto) {
        List<Result> results = voteService.saveOrUpdateVotes(dto);
        return ResponseEntity.ok(results);
    }
}
