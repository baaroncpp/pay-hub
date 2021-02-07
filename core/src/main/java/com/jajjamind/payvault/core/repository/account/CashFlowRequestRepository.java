package com.jajjamind.payvault.core.repository.account;

import com.jajjamind.payvault.core.api.constants.Constants;
import com.jajjamind.payvault.core.jpa.models.account.TCashflowRequest;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;

/**
 * @author akena
 * 05/02/2021
 * 07:43
 **/
@Repository
public interface CashFlowRequestRepository extends CrudRepository<TCashflowRequest,Long> {


    @Lock(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
    @QueryHints({
            @QueryHint(name = Constants.DB_LOCK_TIMEOUT_NAME,value = Constants.DB_LOCK_TIMEOUT)
    })
    @Query("SELECT u from TCashflowRequest u inner join fetch u.fromAccount left join fetch u.toAccount" +
            " left join fetch u.approver1 left join fetch u.agent left join fetch u.product WHERE u.id = :id and u.status = 'PENDING'")
    Optional<TCashflowRequest> findByIdForApproval(@Param("id") Long id);

}
