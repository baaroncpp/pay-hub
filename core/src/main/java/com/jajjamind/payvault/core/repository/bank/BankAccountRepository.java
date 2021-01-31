package com.jajjamind.payvault.core.repository.bank;

import com.jajjamind.payvault.core.jpa.models.bank.TBankAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author akena
 * 17/12/2020
 * 13:05
 **/
@Repository
public interface BankAccountRepository extends CrudRepository<TBankAccount,Integer> {
    @Query("Select u from TBankAccount u where u.accountNumber = :accountNumber")
    Optional<TBankAccount> findByAccountNumber(@Param("accountNumber")String accountNumber);
}
