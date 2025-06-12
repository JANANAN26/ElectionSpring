package com.example.Election.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartyStatsDTO {
    private int partyId;
    private int totalVotes;
    private int totalSeats;
    private double percentage;
}
