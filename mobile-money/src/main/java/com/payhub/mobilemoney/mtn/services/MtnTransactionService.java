package com.payhub.mobilemoney.mtn.services;

import com.payhub.mobilemoney.mtn.jpa.entities.Transaction;
import com.payhub.mobilemoney.mtn.models.DepositModel;
import com.payhub.mobilemoney.mtn.models.WithdrawModel;
import com.payhub.mobilemoney.mtn.mtnModels.AccountHolderBasicInfo;
import com.payhub.mobilemoney.mtn.mtnModels.GetBalanceResponse;
import com.payhub.mobilemoney.mtn.mtnModels.InitiateTransferCompletedRequest;
import com.payhub.mobilemoney.mtn.mtnModels.InitiateTransferResponse;

public interface MtnTransactionService {
    Transaction initiateTransfer(DepositModel depositModel);
    boolean completeCashOutTransaction(InitiateTransferCompletedRequest initiateTransferCompletedRequest);
    Transaction cashInTransaction(WithdrawModel withdrawModel);
    AccountHolderBasicInfo getUserInfo(String msisdn);
    GetBalanceResponse getAccountBalance();
}
