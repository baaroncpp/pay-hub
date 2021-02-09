package com.jajjamind.payvault.core.repository.account;

import com.jajjamind.payvault.core.api.constants.Constants;
import com.jajjamind.payvault.core.jpa.models.account.TAccount;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<TAccount,Long> {

    Optional<TAccount> findByCode(@Param("code") String code);

    List<TAccount> findByAccountGrouping(@Param("accountGrouping") String accountGrouping);

    Optional<TAccount> findByName(@Param("name") String accountGrouping);

    @Query("Select u from TAccount u inner join fetch u.accountGrouping where u.accountGrouping.id = :accountGrouping")
    List<TAccount> findByAccountGrouping(@Param("accountGrouping") Long accountGrouping);


    @Lock(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
    @QueryHints({
            @QueryHint(name = Constants.DB_LOCK_TIMEOUT_NAME,value = Constants.DB_LOCK_TIMEOUT)
    })
    @Query("Select u from TAccount u where id = :id and u.assigned = true and u.accountStatus = 'ACTIVE'")
    Optional<TAccount> findByIdForBalanceUpdate(@Param("id") Long id);

    @Query("Select u from TAccount u where id = :id and u.assigned = true and u.accountStatus = 'ACTIVE' and u.accountType in (:type)")
    Optional<TAccount> findActiveByIdAndType(@Param("id") Long id,@Param("type") String... type);


}
