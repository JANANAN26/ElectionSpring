package com.example.Election.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElectionYearDTO {
    private Integer yearId;
    private Integer electionYear;
    private Integer provinceCount;
    private Integer nowProvinceCount;
}
