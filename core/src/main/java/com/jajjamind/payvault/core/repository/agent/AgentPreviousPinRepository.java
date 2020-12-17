package com.jajjamind.payvault.core.repository.agent;

import com.jajjamind.payvault.core.jpa.models.agent.TAgentPreviousPin;
import org.springframework.data.repository.CrudRepository;

public interface AgentPreviousPinRepository extends CrudRepository<Long, TAgentPreviousPin> {
}
