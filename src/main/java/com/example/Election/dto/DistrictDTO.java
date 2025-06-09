package com.example.Election.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDTO {
    private int distId;
    private int provinceId;
    private String districtName;
    private int districtSeat;

    // Getters and Setters
}
