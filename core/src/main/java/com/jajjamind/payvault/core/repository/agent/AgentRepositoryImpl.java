package com.jajjamind.payvault.core.repository.agent;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;

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
                "WHERE lower(um.first_name) like :firstName AND lower(um.last_name) like :lastName AND lower(um.identification_number) like :idNumber AND " +
                "lower(c.business_name) like :company";

        BigInteger count  = (BigInteger)em.createNativeQuery(query)
                .setParameter("firstName",prefillWithMatchers(firstName))
                .setParameter("lastName",prefillWithMatchers(lastName))
                .setParameter("idNumber",prefillWithMatchers(idNumber))
                .setParameter("company",prefillWithMatchers(company))
                .getSingleResult();

        return count.intValue() > 0;
    }

    @Override
    public Boolean checkThatUsernameIsNotTaken(String userName) {
        final String query = "SELECT count(1) from core.t_agent t WHERE lower(t.username) = lower(:username)";

        int count  = ( (BigInteger)em.createNativeQuery(query)
                .setParameter("username",userName)
                .getSingleResult()).intValue();

        return count < 1;
    }

    private String prefillWithMatchers(String value){
        if(value != null) {
            return "%" + value.toLowerCase() + "%";
        }else{
            return "";
        }
    }
}
