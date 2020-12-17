package com.jajjamind.payvault.core.repository.account;

import com.jajjamind.payvault.core.jpa.models.account.TAccountTransaction;
import org.springframework.data.repository.CrudRepository;

public interface AccountTransactionRepository extends CrudRepository<String, TAccountTransaction> {
}
