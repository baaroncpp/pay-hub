package com.jajjamind.payvault.core.repository.agent;

import com.jajjamind.payvault.core.jpa.models.agent.TTermsAndConditions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TermsAndConditionsRepository extends CrudRepository<TTermsAndConditions,Long> {

    @Query(value = "Select u from TTermsAndConditions u where u.target = 'USER' and u.active = true")
    Optional<TTermsAndConditions> getActiveTermsAndConditionsForUser();
}
