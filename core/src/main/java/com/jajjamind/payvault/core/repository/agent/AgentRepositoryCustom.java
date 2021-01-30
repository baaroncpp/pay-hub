package com.jajjamind.payvault.core.repository.agent;

/**
 * @author akena
 * 20/01/2021
 * 02:18
 **/
public interface AgentRepositoryCustom {

    Boolean checkThatAgentMatchesAny(String firstName,String lastName, String idNumber,String company);

    Boolean checkThatUsernameIsNotTaken(String userName);
}
