package com.example.Election.service.serviceimpl;

import com.example.Election.dto.PartyDTO;
import com.example.Election.entities.Party;
import com.example.Election.repositories.PartyRepository;
import com.example.Election.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyServiceImpl implements PartyService {

    @Autowired
    private PartyRepository partyRepository;

    @Override
    public Party createParty(PartyDTO dto) {
        Party party = new Party();
        party.setPartyName(dto.getPartyName());
        party.setPartyColor(dto.getPartyColor());
        return partyRepository.save(party);
    }

    @Override
    public Party updateParty(int id, PartyDTO dto) {
        Party party = partyRepository.findById(id).orElseThrow(() -> new RuntimeException("Party not found"));
        party.setPartyName(dto.getPartyName());
        party.setPartyColor(dto.getPartyColor());
        return partyRepository.save(party);
    }

    @Override
    public void deleteParty(int id) {
        partyRepository.deleteById(id);
    }

    @Override
    public List<Party> getAllParties() {
        return partyRepository.findAll();
    }
}
