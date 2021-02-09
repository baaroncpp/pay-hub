package com.jajjamind.payvault.core.repository.agent;

import com.jajjamind.payvault.core.jpa.models.agent.TAgentProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentProductRepository extends CrudRepository<TAgentProduct,Long> {

    @Query("Select u from TAgentProduct u inner join fetch u.product inner join fetch u.agent k where u.product.id = :productId and k.id = :agentId" +
            " and u.nonActive = false")
    Optional<TAgentProduct>   findByAgentIdAndProductId(@Param("productId") Long productId,@Param("agentId") Long agentId);

}
