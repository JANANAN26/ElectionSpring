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
@Table(name = "party")
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer partyId;

    @Column(nullable = false, unique = true)
    private String partyName;

    private String partyColor;

    @OneToMany(mappedBy = "party", cascade = CascadeType.ALL)
    private List<Result> results;

    // Getters and Setters
}
