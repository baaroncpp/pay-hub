package com.jajjamind.payvault.core.repository.agent;

import com.jajjamind.payvault.core.jpa.models.agent.TTermsAndConditions;
import org.springframework.data.repository.CrudRepository;

public interface TermsAndConditionsRepository extends CrudRepository<Integer, TTermsAndConditions> {
}
