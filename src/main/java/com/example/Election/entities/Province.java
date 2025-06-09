package com.example.Election.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "province")
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer provinceId;

    @ManyToOne
    @JoinColumn(name = "yearId", nullable = false)
    private ElectionYear electionYear;

    @Column(nullable = false, unique = true)
    private String provinceName;

    private Integer districtCount;
    private Integer nowDistrictCount;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
    private List<District> districts;

    // Getters and Setters

}
