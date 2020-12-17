package com.jajjamind.payvault.core.repository.account;

import com.jajjamind.payvault.core.jpa.models.account.TTransaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<String, TTransaction> {
}
