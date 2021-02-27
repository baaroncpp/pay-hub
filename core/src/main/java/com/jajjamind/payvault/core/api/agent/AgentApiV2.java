package com.jajjamind.payvault.core.api.agent;

import com.jajjamind.payvault.core.api.agent.models.Agent;
import com.jajjamind.payvault.core.service.agent.AgentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

/**
 * @author akena
 * 26/02/2021
 * 00:19
 **/

@Tag(name = "Agent mobile/POS",description = "Agent API for transactional mobile and POS devices")
@RestController
@RequestMapping("/agent")
@RolesAllowed({"ROLE_MOBILE.READ","ROLE_MOBILE.WRITE"})
public class AgentApiV2 {

    @Autowired
    private AgentService agentService;
    //Get the agent's own detail, should use record from agents account
    @GetMapping("/me")
    public Agent getCurrentAgent(@RequestHeader("agent_external_id") String agentExternalId){
        return agentService.getCurrentLoggedInAgent(agentExternalId);
    }

    @RolesAllowed("ROLE_MOBILE_RESET_PIN.WRITE")
    @PostMapping("/password/reset")
    public void resetPassword(@RequestHeader("agent_external_id") String agentExternalId,
                              @RequestParam("oldPassword") String oldPassword,
                              @RequestParam("newPassword") String newPassword)
    {
        agentService.resetAgentPassword(agentExternalId,oldPassword,newPassword);
    }
}
