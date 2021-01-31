package com.jajjamind.payvault.core.repository.agent;

import com.jajjamind.payvault.core.jpa.models.agent.TCompany;

import java.util.Optional;

/**
 * @author akena
 * 19/01/2021
 * 15:55
 **/
public interface CompanyRepositoryCustom {
    Optional<TCompany>  findCompanyByTinNumberAndCompanyName(String tinNumber,String companyName);

    Optional<TCompany>  findByMatchedCompanyName(String companyName);
}
