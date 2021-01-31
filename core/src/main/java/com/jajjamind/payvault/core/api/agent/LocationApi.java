package com.jajjamind.payvault.core.api.agent;

import com.jajjamind.payvault.core.BaseApi;
import com.jajjamind.payvault.core.api.agent.models.District;
import com.jajjamind.payvault.core.service.agent.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author akena
 * 13/01/2021
 * 20:45
 **/
@RestController
@RequestMapping("/location")
public class LocationApi {


    @Autowired
    public DistrictService districtService;


    @GetMapping(value = "/district/all",produces = BaseApi.APPLICATION_JSON)
    public List<District> getAllDistricts(){
        return districtService.getAllDistricts();
    }
}
