package com.jajjamind.payvault.core.repository.agent;

import com.jajjamind.payvault.core.jpa.models.agent.TAgentApproval;
import org.springframework.data.repository.CrudRepository;

public interface AgentApprovalRepository extends CrudRepository<TAgentApproval,Long> {
}
