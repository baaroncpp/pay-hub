package com.jajjamind.payvault.core.repository.account;

import com.jajjamind.payvault.core.jpa.models.account.TAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<TAccount,Long> {

    Optional<TAccount> findByCode(@Param("code") String code);

    List<TAccount> findByAccountGrouping(@Param("accountGrouping") String accountGrouping);

    Optional<TAccount> findByName(@Param("name") String accountGrouping);

    List<TAccount> findByAccountGrouping(@Param("accountGrouping") Long accountGrouping);


}
