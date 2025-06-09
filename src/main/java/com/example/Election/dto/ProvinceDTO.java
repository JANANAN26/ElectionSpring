package com.example.Election.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceDTO {
    private Integer provinceId;
    private String provinceName;
    private Integer yearId;
    private int districtCount;
    private int nowDistrictCount;

    // Getters and Setters
}
