package com.example.Election.service.serviceimpl;

import com.example.Election.dto.ElectionYearDTO;
import com.example.Election.entities.ElectionYear;
import com.example.Election.repositories.ElectionYearRepository;
import com.example.Election.service.ElectionYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElectionYearServiceImpl implements ElectionYearService {

    private final ElectionYearRepository repo;

    private ElectionYearDTO toDTO(ElectionYear e) {
        return new ElectionYearDTO(
                e.getYearId(), e.getElectionYear(), e.getProvinceCount(), e.getNowProvinceCount()
        );
    }

    private ElectionYear toEntity(ElectionYearDTO dto) {
        ElectionYear e = new ElectionYear();
        e.setElectionYear(dto.getElectionYear());
        e.setProvinceCount(dto.getProvinceCount() != null ? dto.getProvinceCount() : 0);
        e.setNowProvinceCount(dto.getNowProvinceCount() != null ? dto.getNowProvinceCount() : 0);
        return e;
    }

    @Override
    public ElectionYearDTO addYear(ElectionYearDTO dto) {
        repo.findByElectionYear(dto.getElectionYear()).ifPresent(e ->
                { throw new RuntimeException("Election year already exists."); }
        );
        ElectionYear saved = repo.save(toEntity(dto));
        return toDTO(saved);
    }

    @Override
    public List<ElectionYearDTO> getAllYears() {
        return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public ElectionYearDTO getYearById(Integer id) {
        return repo.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Election year not found."));
    }
}
