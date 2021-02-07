package com.jajjamind.payvault.core.service.account;


import com.jajjamind.commons.utils.RealTimeUtil;
import com.jajjamind.payvault.core.jpa.models.account.TAccountTransaction;
import com.jajjamind.payvault.core.repository.account.AccountTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author akena
 * 07/02/2021
 * 10:37
 **/
@Service
public class AccountTransactionServiceImpl implements AccountTransactionService {

    @Autowired
    AccountTransactionRepository accountTransactionRepository;


    @Override
    public TAccountTransaction save(TAccountTransaction accountTransaction) {

        final String id = RealTimeUtil.generateTransactionId();
        accountTransaction.setExternalTransactionId(id);

        accountTransactionRepository.save(accountTransaction);

        return accountTransaction;
    }
}
