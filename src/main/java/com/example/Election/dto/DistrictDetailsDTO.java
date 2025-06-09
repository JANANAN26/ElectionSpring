package com.example.Election.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDetailsDTO {
    private Integer districtdetailsId;
    private Integer distId;
    private Integer totalVotes;
    private Integer rejectVotes;
    private Integer validVotes;
}
