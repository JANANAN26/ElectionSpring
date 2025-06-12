package com.example.Election.service.serviceimpl;

import com.example.Election.dto.ProvinceDTO;
import com.example.Election.entities.Province;
import com.example.Election.repositories.ProvinceRepository;
import com.example.Election.service.ProvinceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepo;

    @Override
    public String addProvince(ProvinceDTO dto) {
        Province province = new Province();
        province.setProvinceName(dto.getProvinceName());

        // Save province
        provinceRepo.save(province);

        return "Province added successfully";
    }

    @Override
    public String updateProvince(ProvinceDTO dto) {
        Province province = provinceRepo.findById(dto.getProvinceId()).orElse(null);
        if (province == null) return "Province not found";

        province.setProvinceName(dto.getProvinceName());
        provinceRepo.save(province);
        return "Province updated successfully";
    }

    @Override
    public ProvinceDTO getProvinceById(Integer id) {
        Province province = provinceRepo.findById(id).orElse(null);
        if (province == null) return null;

        ProvinceDTO dto = new ProvinceDTO();
        dto.setProvinceId(province.getProvinceId());
        dto.setProvinceName(province.getProvinceName());
        return dto;
    }

    @Override
    public List<ProvinceDTO> getAllProvinces() {
        return provinceRepo.findAll().stream().map(p -> {
            ProvinceDTO dto = new ProvinceDTO();
            dto.setProvinceId(p.getProvinceId());
            dto.setProvinceName(p.getProvinceName());
            return dto;
        }).collect(Collectors.toList());
    }
}
