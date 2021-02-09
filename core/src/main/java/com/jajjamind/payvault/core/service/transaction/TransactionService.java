package com.jajjamind.payvault.core.service.transaction;

import com.jajjamind.payvault.core.api.transaction.models.Transaction;
import com.jajjamind.payvault.core.api.transaction.models.TransactionResponse;

/**
 * @author akena
 * 08/02/2021
 * 01:15
 **/
public interface TransactionService {

    TransactionResponse createTransaction(Transaction request);
}
