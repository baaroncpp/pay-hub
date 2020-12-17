package com.jajjamind.payvault.core.repository.account;

import com.jajjamind.payvault.core.jpa.models.account.TAccount;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Long, TAccount> {
}
