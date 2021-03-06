package com.jajjamind.payvault.core.repository.bank;

import com.jajjamind.payvault.core.jpa.models.bank.TBankDeposit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author akena
 * 17/12/2020
 * 13:06
 **/
@Repository
public interface BankDepositRepository extends CrudRepository<TBankDeposit,String> {
}
