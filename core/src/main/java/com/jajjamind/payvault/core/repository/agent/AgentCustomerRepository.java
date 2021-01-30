package com.jajjamind.payvault.core.repository.agent;

import com.jajjamind.payvault.core.jpa.models.agent.TAgentCustomer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author akena
 * 17/12/2020
 * 12:58
 **/
@Repository
public interface AgentCustomerRepository extends CrudRepository<TAgentCustomer,String> {
}
