package com.example.Election.service;

import com.example.Election.dto.PartyDTO;
import com.example.Election.entities.Party;

import java.util.List;

public interface PartyService {
    Party createParty(PartyDTO partyDTO);
    Party updateParty(int partyId, PartyDTO partyDTO);
    void deleteParty(int partyId);
    List<Party> getAllParties();
}
