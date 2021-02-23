package com.jajjamind.payvault.core.service.agent;

import com.jajjamind.payvault.core.api.agent.models.Company;
import com.jajjamind.payvault.core.jpa.models.RecordList;
import com.jajjamind.payvault.core.service.BaseApiService;
import org.springframework.util.MultiValueMap;

/**
 * @author akena
 * 19/01/2021
 * 12:20
 **/
public interface CompanyService extends BaseApiService<Company> {
    RecordList getAllCompanies(MultiValueMap map);
    Company findByBusinessName(String businessName);
    Company findByTinNumber(String tinNumber);
}
