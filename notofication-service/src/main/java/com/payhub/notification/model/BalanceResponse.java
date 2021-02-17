package com.payhub.notification.model;


import com.payhub.notification.constants.Currency;

public class BalanceResponse {
    private String user;
    private Currency currency;
    private String balance;

    public BalanceResponse() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
