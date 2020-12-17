package com.jajjamind.payvault.core.repository.agent;

import com.jajjamind.payvault.core.jpa.models.agent.TAgentPreviousPin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentPreviousPinRepository extends CrudRepository<TAgentPreviousPin,Long> {
}
