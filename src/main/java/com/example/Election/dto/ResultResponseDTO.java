package com.example.Election.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultResponseDTO {

    private Integer distId;
    private String districtName;
    private Integer totalVotes;
    private Integer rejectVotes;
    private Integer validVotes;
    private List<PartyResultDTO> partyVotes;
}
