package com.jajjamind.payvault.core.repository.account;

import com.jajjamind.payvault.core.jpa.models.account.TAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<TAccount,Long> {
}
