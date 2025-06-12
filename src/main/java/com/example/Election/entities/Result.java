package com.example.Election.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer r_id;

    @ManyToOne
    @JoinColumn(name = "distId", nullable = false)
    private District district;

    @ManyToOne
    @JoinColumn(name = "partyId", nullable = false)
    private Party party;

    private Integer partyVotes;
    private Integer bonusSeats;
    private Integer firstSeatAllocation;
    private Integer secondSeatAllocation;
    private Integer finalSeatAllocation;
}
