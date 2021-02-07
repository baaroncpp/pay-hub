package com.jajjamind.payvault.core.service.agent;

import com.jajjamind.payvault.core.api.agent.models.Agent;

import com.jajjamind.payvault.core.jpa.models.RecordList;
import com.jajjamind.payvault.core.repository.agent.JooqAgentRepository;
import com.jajjamind.payvault.core.service.BaseApiService;
import org.springframework.util.MultiValueMap;

/**
 * @author akena
 * 19/01/2021
 * 02:54
 **/
public interface AgentService extends BaseApiService<Agent> {

    RecordList<JooqAgentRepository.Result> queryForAgents(MultiValueMap<String,?> k);
    void approveOrRejectAgentCreation(Long agentId,String status,String comment);

}
