package com.payhub.mobilemoney.airtel.service;

import com.payhub.mobilemoney.airtel.airtelModels.FetchUserRequest;
import com.payhub.mobilemoney.airtel.airtelModels.FetchUserResponse;
import com.payhub.mobilemoney.airtel.jpa.entities.Transaction;
import com.payhub.mobilemoney.airtel.models.DepositTransactionModel;
import com.payhub.mobilemoney.airtel.models.WithDrawTransactionModel;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface AirtelTransactionService {
    Transaction makeDeposit(DepositTransactionModel cashInModel);
    List<Transaction> getTransactions(Pageable pageable, Date from, Date to);
    Transaction getTransaction(String id);
    Transaction getTransactionStatus(String id);
    FetchUserResponse fetchUserInfo(String msisdn);
    Transaction makeWithDraw(WithDrawTransactionModel withDrawTransactionModel);
}
