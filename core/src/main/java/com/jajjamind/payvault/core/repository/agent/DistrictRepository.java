package com.jajjamind.payvault.core.repository.agent;

import com.jajjamind.payvault.core.jpa.models.agent.TDistrict;
import org.springframework.data.repository.CrudRepository;

/**
 * @author akena
 * 17/12/2020
 * 13:03
 **/
public interface DistrictRepository extends CrudRepository<Long, TDistrict> {
}
