package com.example.Election.service.serviceimpl;

import com.example.Election.dto.ProvinceDTO;
import com.example.Election.entities.ElectionYear;
import com.example.Election.entities.Province;
import com.example.Election.repositories.ElectionYearRepository;
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

    @Autowired
    private ElectionYearRepository yearRepo;

    @Override
    public String addProvince(ProvinceDTO dto) {
        ElectionYear year = yearRepo.findById(dto.getYearId()).orElse(null);
        if (year == null) return "Year not found";

        if (year.getNowProvinceCount() <= 0) return "provinceCount limit reached";

        Province province = new Province();
        BeanUtils.copyProperties(dto, province);
        province.setElectionYear(year);


        // Save the province here
        provinceRepo.save(province);

        // Then update year count
        year.setNowProvinceCount(year.getNowProvinceCount() - 1);
        yearRepo.save(year);

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
            dto.setDistrictCount(p.getDistrictCount());
            dto.setNowDistrictCount(p.getNowDistrictCount());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void updateDistrictCount(int provinceId, int districtCount) {
        Province p = provinceRepo.findById(provinceId).orElseThrow();
        p.setDistrictCount(districtCount);
        p.setNowDistrictCount(districtCount);
        provinceRepo.save(p);
    }
}
