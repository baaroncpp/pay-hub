package com.jajjamind.payvault.core.repository.bank;

import com.jajjamind.payvault.core.jpa.models.account.TAccountMapping;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountMappingRepository extends CrudRepository<TAccountMapping,Integer> {

    @Query("Select u from TAccountMapping u where u.status = 'ACTIVE' AND u.bankId = :bankId")
    Optional<TAccountMapping> findBankAccountMapped(@Param("bankId") Integer bankId);

    @Query("Select u from TAccountMapping u where u.status = 'ACTIVE' AND u.agentId = :agentId")
    Optional<TAccountMapping> findAgentAccountMapped(@Param("agentId") Long agentId);

    @Query("Select u from TAccountMapping u where u.status = 'ACTIVE' AND u.productId = :productId")
    Optional<TAccountMapping> findProductAccountMapped(@Param("productId") Integer productId);

    @Query("Select u from TAccountMapping u inner join fetch u.accountId WHERE u.status = 'ACTIVE' AND u.systemAccount = false AND u.agentIdCommission = :agentId")
    Optional<TAccountMapping> findAgentCommissionAccountMapped(@Param("agentId") Long agentId);

    @Query("Select u from TAccountMapping u inner join fetch u.accountId where u.status = 'ACTIVE' AND u.systemAccount = true")
    Optional<TAccountMapping> findSystemCommissionAccountMapped();

}
