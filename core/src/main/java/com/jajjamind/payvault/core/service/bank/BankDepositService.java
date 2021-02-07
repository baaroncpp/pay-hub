package com.jajjamind.payvault.core.service.bank;

import com.jajjamind.payvault.core.api.account.models.Account;
import com.jajjamind.payvault.core.api.bank.models.BankDeposit;
import com.jajjamind.payvault.core.api.users.models.Approval;

/**
 * @author akena
 * 02/02/2021
 * 00:51
 **/
public interface BankDepositService {

    BankDeposit initiateDeposit(BankDeposit deposit);
    Account fetchAccountLinkedToBank(Integer bankId);
    Account linkMainAccountToBank(Long accountId,Integer bankId);
    Account unlinkMainAccountFromBank(Long accountId,Integer bankId);
    void approveBankDeposit(Approval approval);

}
