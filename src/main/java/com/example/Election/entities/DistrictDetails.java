package com.example.Election.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "districtdetails")
public class DistrictDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer districtdetailsId;

    @ManyToOne
    @JoinColumn(name = "distId", nullable = false)
    private District district;

    private Integer totalVotes;
    private Integer rejectVotes;

    // Getters and Setters
}
