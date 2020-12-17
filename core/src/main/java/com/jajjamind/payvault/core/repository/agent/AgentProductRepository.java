package com.jajjamind.payvault.core.repository.agent;

import com.jajjamind.payvault.core.jpa.models.agent.TAgentProduct;
import org.springframework.data.repository.CrudRepository;

public interface AgentProductRepository extends CrudRepository<Long, TAgentProduct> {
}
