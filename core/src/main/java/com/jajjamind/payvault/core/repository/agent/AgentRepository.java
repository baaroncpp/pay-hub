package com.jajjamind.payvault.core.repository.agent;

import com.jajjamind.payvault.core.jpa.models.agent.TAgent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author akena
 * 17/12/2020
 * 12:57
 **/
@Repository
public interface AgentRepository extends CrudRepository<TAgent,Long>,AgentRepositoryCustom {

    @Query("Select u from TAgent u where u.username = :username")
    Optional<TAgent> findByUserName(@Param("username") String userName);

    @Query("Select u from TAgent u inner join fetch u.userMeta where u.approvalStatus = 'APPROVED' AND u.type = 'SUPER_AGENT' AND u.nonLocked = true AND u.nonDisabled = true")
    List<TAgent> findSuperAgents();

    @Query("Select u from TAgent u inner join fetch u.userMeta left join fetch u.company where u.id = :id")
    Optional<TAgent> findAgentWithAllDetails(@Param("id") Long id);

}
