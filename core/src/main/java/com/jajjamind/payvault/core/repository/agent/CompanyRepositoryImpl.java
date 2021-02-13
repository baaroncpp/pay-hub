package com.jajjamind.payvault.core.repository.agent;

import com.jajjamind.payvault.core.jpa.models.agent.TCompany;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
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

        final String query = "SELECT * FROM core.t_company WHERE tin_number like CONCAT('%',:tinNumber,'%') or business_name like CONCAT('%',:businessName,'%')";

        Query nativeQuery = em.createNativeQuery(query,TCompany.class)
                .setParameter("tinNumber",tinNumber)
                .setParameter("businessName",companyName);


        final List result = nativeQuery.getResultList();

        return result.isEmpty() ? Optional.empty():Optional.of((TCompany)result.get(0));
    }

    @Override
    public Optional<TCompany> findByMatchedCompanyName( String companyName) {
        final String query = "SELECT * FROM core.t_company WHERE business_name like CONCAT('%',:businessName,'%')";

        Query nativeQuery = em.createNativeQuery(query,TCompany.class)
                .setParameter("businessName",companyName);

        final List result = nativeQuery.getResultList();
        return result.isEmpty() ? Optional.empty():Optional.of((TCompany)result.get(0));
    }

}
