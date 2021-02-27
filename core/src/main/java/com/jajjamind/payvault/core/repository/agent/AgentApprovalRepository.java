package com.jajjamind.payvault.core.repository.agent;

import com.jajjamind.payvault.core.jpa.models.agent.TAgentApproval;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AgentApprovalRepository extends CrudRepository<TAgentApproval,Long> {

    @Query("Select u from TAgentApproval u inner join fetch u.agent a left join fetch u.approver1 left join fetch u.approver2 where a.id = :id and u.status = 'PENDING'")
    Optional<TAgentApproval> findByIdWithAgent(@Param("id") Long id);
}
