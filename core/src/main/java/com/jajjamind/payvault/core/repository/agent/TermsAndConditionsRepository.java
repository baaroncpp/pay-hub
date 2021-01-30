package com.jajjamind.payvault.core.repository.agent;

import com.jajjamind.payvault.core.jpa.models.agent.TTermsAndConditions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermsAndConditionsRepository extends CrudRepository<TTermsAndConditions,Integer> {
}
