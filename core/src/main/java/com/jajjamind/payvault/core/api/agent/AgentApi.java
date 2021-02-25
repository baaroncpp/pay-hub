package com.jajjamind.payvault.core.api.agent;

import com.jajjamind.payvault.core.BaseApi;
import com.jajjamind.payvault.core.api.agent.models.Agent;
import com.jajjamind.payvault.core.api.users.models.Approval;
import com.jajjamind.payvault.core.api.users.models.TermsAndConditions;
import com.jajjamind.payvault.core.jpa.models.RecordList;
import com.jajjamind.payvault.core.repository.agent.JooqAgentRepository;
import com.jajjamind.payvault.core.service.agent.AgentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author akena
 * 11/01/2021
 * 15:00
 **/
@Tag(name = "Agent",description = "Agent Management")
@RestController
@RequestMapping("/agent")
public class AgentApi{

    @Autowired
    public AgentService agentService;

    @PostMapping(value = "/ordinary",produces = BaseApi.APPLICATION_JSON)
    public Agent addOrdinaryAgent(Agent agent) {
        return agentService.add(agent);
    }

    @PostMapping(value = "/super",produces = BaseApi.APPLICATION_JSON)
    public Agent addSuperAgent(Agent agent) {
        return agentService.addSuperAgent(agent);
    }

    @PutMapping(value = "/",produces = BaseApi.APPLICATION_JSON)
    public Agent update(Agent agent) {
        return agentService.update(agent);
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

    @GetMapping("/super/all")
    public List<Agent> getSuperAgents(){
        return agentService.getSuperAgents();
    }


    @GetMapping(value = "/query",produces = BaseApi.APPLICATION_JSON)
    public RecordList<JooqAgentRepository.Result> queryForAgent(
            @RequestParam MultiValueMap<String,?> requestParams
            ){
        return agentService.queryForAgents(requestParams);
    }

    @GetMapping(value = "/{username}/check")
    public boolean checkIfAgentUserNameIsTaken(@PathVariable("username") String username){
        return agentService.isUserNameTaken(username);
    }

    @PostMapping("approve")
    public void approveAgent(@RequestBody Approval approval){
        agentService.approveOrRejectAgentCreation(approval);
    }


    @GetMapping("/approve/pending")
    public RecordList getAgentsPendingApproval(@RequestParam MultiValueMap map){

        return agentService.getAgentsPendingApproval(map);
    }

    @GetMapping("/termsofuse")
    public TermsAndConditions getTermsOfUse(){
        return agentService.getTermsOfService();
    }

    @PostMapping("/{agentId}/pin/regenerate")
    public void regenerateAgentPin(@PathVariable("agentId") Long agentId){
        agentService.regenerateAgentPassword(agentId);
    }

}
