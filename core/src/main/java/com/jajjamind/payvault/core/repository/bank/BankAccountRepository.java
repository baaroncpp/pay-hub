package com.jajjamind.payvault.core.repository.bank;

import com.jajjamind.payvault.core.jpa.models.bank.TBankAccount;
import org.springframework.data.repository.CrudRepository;

/**
 * @author akena
 * 17/12/2020
 * 13:05
 **/
public interface BankAccountRepository extends CrudRepository<Integer, TBankAccount> {
}
