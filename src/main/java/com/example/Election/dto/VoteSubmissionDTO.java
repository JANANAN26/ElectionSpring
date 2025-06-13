package com.example.Election.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteSubmissionDTO {
    private Integer districtId;
    private Integer totalVotes;
    private Integer rejectVotes;
    private List<PartyVoteDTO> partyVotes;
}
