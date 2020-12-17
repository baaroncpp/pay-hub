package com.jajjamind.payvault.core.repository.bank;

import com.jajjamind.payvault.core.jpa.models.bank.TBankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author akena
 * 17/12/2020
 * 13:05
 **/
@Repository
public interface BankAccountRepository extends CrudRepository<TBankAccount,Integer> {
}
