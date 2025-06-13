package com.example.Election.service.serviceimpl;

import com.example.Election.dto.DistrictDTO;
import com.example.Election.entities.District;
import com.example.Election.entities.Province;
import com.example.Election.repositories.DistrictRepository;
import com.example.Election.repositories.ProvinceRepository;
import com.example.Election.service.DistrictService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictRepository districtRepo;

    @Autowired
    private ProvinceRepository provinceRepo;

    @Override
    public List<DistrictDTO> getAllDistricts() {
        return districtRepo.findAll().stream().map(d -> {
            DistrictDTO dto = new DistrictDTO();
            BeanUtils.copyProperties(d, dto);
            dto.setProvinceId(d.getProvince().getProvinceId());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void addDistrict(DistrictDTO dto) {
        Province province = provinceRepo.findById(dto.getProvinceId())
                .orElseThrow(() -> new RuntimeException("Province not found with ID: " + dto.getProvinceId()));

        District district = new District();
        district.setDistrictName(dto.getDistrictName());
        district.setDistrictSeat(dto.getDistrictSeat());
        district.setProvince(province);

        districtRepo.save(district);
    }

    @Override
    public void updateDistrictName(int distId, String districtName) {
        District d = districtRepo.findById(distId)
                .orElseThrow(() -> new RuntimeException("District not found with ID: " + distId));
        d.setDistrictName(districtName);
        districtRepo.save(d);
    }
}
