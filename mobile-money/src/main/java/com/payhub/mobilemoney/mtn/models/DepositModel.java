package com.payhub.mobilemoney.mtn.models;

import com.payhub.mobilemoney.mtn.constants.Currency;

public class DepositModel {
    private String msisdn;
    private double amount;
    private Currency currency;

    public DepositModel() {
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
