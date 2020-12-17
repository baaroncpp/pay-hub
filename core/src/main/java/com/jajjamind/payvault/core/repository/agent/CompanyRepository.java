package com.jajjamind.payvault.core.repository.agent;

import com.jajjamind.payvault.core.jpa.models.agent.TCompany;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Long,TCompany> {
}
