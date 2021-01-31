package com.jajjamind.payvault.core.service.agent;

import com.jajjamind.payvault.core.api.agent.models.District;
import com.jajjamind.payvault.core.jpa.models.agent.TDistrict;
import com.jajjamind.payvault.core.repository.agent.DistrictRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author akena
 * 13/01/2021
 * 20:40
 **/

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    public DistrictRepository districtRepository;

    @Override
    public List<District> getAllDistricts() {
        Iterable<TDistrict> d = districtRepository.findAll();

        List<District> districts = new ArrayList<>();
        d.forEach(district -> {
            District item = new District();
            BeanUtils.copyProperties(district,item);
            districts.add(item);
        });

        return districts;
    }
}
