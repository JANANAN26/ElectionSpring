package com.example.Election.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalVotesDTO {
    private int totalVotes;
    private int totalValidVotes;
    private int totalRejectVotes;
    private int totalSeats;

    // Getters and setters
}
