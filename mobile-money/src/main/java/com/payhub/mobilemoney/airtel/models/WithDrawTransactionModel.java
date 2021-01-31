package com.payhub.mobilemoney.airtel.models;


public class WithDrawTransactionModel {
    private String msisdn;
    private double amount;
    private String opt;

    public WithDrawTransactionModel() {
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

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }
}
