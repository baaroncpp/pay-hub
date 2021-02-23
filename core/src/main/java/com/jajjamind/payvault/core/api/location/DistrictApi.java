package com.jajjamind.payvault.core.api.location;

import com.jajjamind.payvault.core.BaseApi;
import com.jajjamind.payvault.core.api.agent.models.District;
import com.jajjamind.payvault.core.service.agent.DistrictService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author akena
 * 11/01/2021
 * 15:04
 **/

@Tag(name = "Districts",description = "Get all districts that are in the system")
@RestController
@RequestMapping("/district")
public class DistrictApi {

    @Autowired
    DistrictService districtService;

    @GetMapping(value = "/all",produces = BaseApi.APPLICATION_JSON)
    List<District> getAllDistricts(){
        return districtService.getAllDistricts();
    }

}
