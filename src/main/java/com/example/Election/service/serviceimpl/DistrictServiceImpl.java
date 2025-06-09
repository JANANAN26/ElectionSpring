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
            BeanUtils.copyProperties(d,dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void addDistrict(DistrictDTO dto) {
        Province province = provinceRepo.findById(dto.getProvinceId()).orElseThrow(() ->
                new RuntimeException("Province not found with ID: " + dto.getProvinceId()));

        if (province.getNowDistrictCount() > 0) {
            District d = new District();

            // Avoid copying ID and provinceId
            d.setDistrictName(dto.getDistrictName());
            d.setDistrictSeat(dto.getDistrictSeat());
            d.setProvince(province); // ✅ properly set province

            districtRepo.save(d);

            province.setNowDistrictCount(province.getNowDistrictCount() - 1);
            provinceRepo.save(province);
        } else {
            throw new RuntimeException("District count limit reached");
        }
    }


    @Override
    public void updateDistrictName(int distId, String districtName) {
        District d = districtRepo.findById(distId).orElseThrow();
        d.setDistrictName(districtName);
        districtRepo.save(d);
    }
}
