package com.jajjamind.payvault.core.api.agent;

import com.jajjamind.payvault.core.BaseApi;
import com.jajjamind.payvault.core.api.agent.models.Agent;
import com.jajjamind.payvault.core.jpa.models.RecordList;
import com.jajjamind.payvault.core.repository.agent.JooqAgentRepository;
import com.jajjamind.payvault.core.service.agent.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author akena
 * 11/01/2021
 * 15:00
 **/
@RestController
@RequestMapping("/agent")
public class AgentApi implements BaseApi<Agent> {

    @Autowired
    public AgentService agentService;

    @Override
    public Agent add(Agent agent) {
        return agentService.add(agent);
    }

    @Override
    public Agent get(Long id) {
      throw new UnsupportedOperationException();
    }

    @Override
    public Agent update(Agent agent) {
        return agentService.update(agent);
    }

    @Override
    public Agent delete(long id) {
        return null;
    }

    @Override
    public List<Agent> getAll() {
       throw new UnsupportedOperationException();
    }

    /**
     * Important params include
     * sortOrder
     * sortBy
     * showList
     * limit
     * offset
     * requiredColumns
     */

    @GetMapping(value = "/query",produces = APPLICATION_JSON)
    public RecordList<JooqAgentRepository.Result> queryForAgent(
            @RequestParam MultiValueMap<String,?> requestParams
            ){
        return agentService.queryForAgents(requestParams);
    }

}