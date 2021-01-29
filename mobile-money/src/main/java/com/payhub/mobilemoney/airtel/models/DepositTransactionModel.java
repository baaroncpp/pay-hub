package com.payhub.mobilemoney.airtel.models;

import lombok.Data;

@Data
public class DepositTransactionModel {
    private String payerMsisdn;//no country code
    private double amount;

    public DepositTransactionModel() {
    }

    public String getPayerMsisdn() {
        return payerMsisdn;
    }

    public void setPayerMsisdn(String payerMsisdn) {
        this.payerMsisdn = payerMsisdn;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
