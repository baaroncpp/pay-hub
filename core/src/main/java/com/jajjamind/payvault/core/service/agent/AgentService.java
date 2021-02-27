package com.jajjamind.payvault.core.service.agent;

import com.jajjamind.payvault.core.api.agent.models.Agent;

import com.jajjamind.payvault.core.api.users.models.Approval;
import com.jajjamind.payvault.core.api.users.models.TermsAndConditions;
import com.jajjamind.payvault.core.jpa.models.RecordList;
import com.jajjamind.payvault.core.jpa.models.enums.ApprovalEnum;
import com.jajjamind.payvault.core.repository.agent.JooqAgentRepository;
import com.jajjamind.payvault.core.service.BaseApiService;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 * @author akena
 * 19/01/2021
 * 02:54
 **/
public interface AgentService extends BaseApiService<Agent> {

    RecordList<JooqAgentRepository.Result> queryForAgents(MultiValueMap<String,?> k);
    void approveOrRejectAgentCreation(Approval approval);
    boolean isUserNameTaken(String username);
    Agent addSuperAgent(Agent agent);
    List<Agent> getSuperAgents();
    RecordList getAgentsPendingApproval(MultiValueMap map);
    TermsAndConditions getTermsOfService();
    Agent getCurrentLoggedInAgent(String id);
    void resetAgentPassword(String externalId,String oldPassword,String newPassword);
    void regenerateAgentPassword(Long agentId);
}
