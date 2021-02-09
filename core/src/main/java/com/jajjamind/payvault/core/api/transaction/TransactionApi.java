package com.jajjamind.payvault.core.api.transaction;

import com.jajjamind.payvault.core.BaseApi;
import com.jajjamind.payvault.core.api.transaction.models.*;
import com.jajjamind.payvault.core.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author akena
 * 30/01/2021
 * 03:55
 **/
@RequestMapping("/transaction")
@RestController
public class TransactionApi {

    @Autowired
    TransactionService transactionService;

    @PostMapping(value = "/",consumes = BaseApi.APPLICATION_JSON,produces = BaseApi.APPLICATION_JSON)
    public TransactionResponse createTransaction(Transaction transaction){
        return transactionService.createTransaction(transaction);
    }


}
