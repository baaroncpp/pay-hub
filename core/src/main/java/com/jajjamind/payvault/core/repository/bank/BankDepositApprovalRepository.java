package com.jajjamind.payvault.core.repository.bank;

import com.jajjamind.payvault.core.jpa.models.bank.TBankDepositApproval;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author akena
 * 02/02/2021
 * 12:00
 **/
@Repository
public interface BankDepositApprovalRepository extends CrudRepository<TBankDepositApproval,Long> {

    @Query("Select u from TBankDepositApproval u inner join fetch u.bankDeposit inner join fetch u.bankDeposit.bank where u.id = :id")
    Optional<TBankDepositApproval> findBankDepositWithBankDetails(@Param("id") Long id);
}
