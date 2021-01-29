package com.payhub.mobilemoney.airtel.models;

import lombok.Data;

@Data
public class DepositTransactionModel {
    private String payerMsisdn;//no country code
    private double amount;
}
