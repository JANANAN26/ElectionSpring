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
@Table(name = "district")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer distId;

    @ManyToOne
    @JoinColumn(name = "provinceId", nullable = false)
    private Province province;

    private String districtName;
    private Integer districtSeat;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    private List<DistrictDetails> districtDetails;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    private List<Result> results;

    // Getters and Setters
}
