package com.example.Election.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "electionyear")
public class ElectionYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer yearId;

    @Column(nullable = false, unique = true)
    private Integer electionYear;

    private Integer provinceCount = 0;

    private Integer nowProvinceCount = 0;

}
