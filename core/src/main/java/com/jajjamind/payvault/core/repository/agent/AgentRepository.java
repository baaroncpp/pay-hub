package com.jajjamind.payvault.core.repository.agent;

import com.jajjamind.payvault.core.jpa.models.agent.TAgent;
import org.springframework.data.repository.CrudRepository;

/**
 * @author akena
 * 17/12/2020
 * 12:57
 **/
public interface AgentRepository extends CrudRepository<Long, TAgent> {
}
