package com.jajjamind.payvault.core.service.account;

import com.jajjamind.payvault.core.jpa.models.account.TAccountTransaction;

/**
 * @author akena
 * 07/02/2021
 * 10:36
 **/
public interface AccountTransactionService {
    TAccountTransaction save(TAccountTransaction tAccountTransaction);
}
