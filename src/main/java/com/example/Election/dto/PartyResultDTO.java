package com.example.Election.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartyResultDTO {

    private String partyName;
    private Integer partyVotes;
    private String partyLogo;
    private Integer bonusSeats;
    private Integer firstSeatAllocation;
    private Integer secondSeatAllocation;
    private Integer finalSeatAllocation;
    private Double percentage;
    private String status;

    // Getters and setters
}