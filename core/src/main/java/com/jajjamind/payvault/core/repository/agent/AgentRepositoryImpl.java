package com.jajjamind.payvault.core.repository.agent;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author akena
 * 20/01/2021
 * 02:27
 **/
public class AgentRepositoryImpl implements AgentRepositoryCustom {

    @PersistenceContext
    public EntityManager em;

    @Override
    public Boolean checkThatAgentMatchesAny(String firstName, String lastName, String idNumber, String company) {

        final String query = "SELECT count(1) from core.t_agent a inner join core.t_user_meta um on a.id = um.agent_id inner join core.t_company c on c.id = a.company_id " +
                "WHERE um.first_name like '%:firstName%' AND um.last_name like '%:lastName%' AND um.identification_number like '%:idNumber%' AND c.business_name like '%:company%'";

        Integer count  = (Integer)em.createNativeQuery(query,Integer.class)
                .setParameter("firstName",firstName)
                .setParameter("lastName",lastName)
                .setParameter("idNumber",idNumber)
                .setParameter("company",company)
                .getSingleResult();

        return count > 0;
    }

    @Override
    public Boolean checkThatUsernameIsNotTaken(String userName) {
        final String query = "SELECT count(1) from core.t_agent t WHERE t.username = :username";

        Integer count  = (Integer)em.createNativeQuery(query,Integer.class)
                .setParameter("username",userName)
                .getSingleResult();

        return count < 1;
    }
}
