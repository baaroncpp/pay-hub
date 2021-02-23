package com.jajjamind.payvault.core.repository.agent;

import com.jajjamind.payvault.core.jpa.models.agent.TCompany;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends CrudRepository<TCompany,Long>,CompanyRepositoryCustom  {

    @Query("Select u from TCompany u where lower(u.businessName) = lower(:businessName)")
    Optional<TCompany> findByBusinessName(@Param("businessName") String businessName);

    Optional<TCompany> findByTinNumber(@Param("tinNumber") String tinNumber);

}
