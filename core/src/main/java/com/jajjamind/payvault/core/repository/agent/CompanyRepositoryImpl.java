package com.jajjamind.payvault.core.repository.agent;

import com.jajjamind.payvault.core.jpa.models.agent.TCompany;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

/**
 * @author akena
 * 19/01/2021
 * 15:57
 **/
public class CompanyRepositoryImpl implements CompanyRepositoryCustom {

    @PersistenceContext
    public EntityManager em;

    @Override
    public Optional<TCompany> findCompanyByTinNumberAndCompanyName(String tinNumber, String companyName) {

        final String query = "SELECT * FROM core.t_customer WHERE tin_number like '%:tinNumber%' or business_name like '%:businessName%'";

        Query nativeQuery = em.createNativeQuery(query,TCompany.class)
                .setParameter("tinNumber",tinNumber)
                .setParameter("businessName",companyName);

        final TCompany result = (TCompany) nativeQuery.getSingleResult();

        return Optional.of(result);
    }

    @Override
    public Optional<TCompany> findByMatchedCompanyName( String companyName) {
        final String query = "SELECT * FROM core.t_customer WHERE business_name like '%:businessName%'";

        Query nativeQuery = em.createNativeQuery(query,TCompany.class)
                .setParameter("businessName",companyName);

        final TCompany result = (TCompany) nativeQuery.getSingleResult();

        return Optional.of(result);
    }

}
