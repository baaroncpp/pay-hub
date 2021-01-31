package com.payhub.mobilemoney.mtn.mtnModels;

import com.payhub.mobilemoney.mtn.constants.Currency;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "amount")
public class Amount {
    private double amount;
    private Currency currency;

    public Amount() {
    }

    public Amount(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
