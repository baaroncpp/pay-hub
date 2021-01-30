package com.jajjamind.payvault.core.repository.account;

import com.jajjamind.payvault.core.jpa.models.account.TAccountGrouping;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountGroupingRepository extends CrudRepository< TAccountGrouping,Long> {

    Optional<TAccountGrouping> findByName(@Param("name") String name);
}
