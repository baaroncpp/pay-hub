package com.payhub.mobilemoney.mtn.mtnModels;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "getbalanceresponse")
public class GetBalanceResponse {
    private Balance balance;

    public GetBalanceResponse() {
    }

    public Balance getBalance() {
        return balance;
    }

    @XmlElement(name = "balance")
    public void setBalance(Balance balance) {
        this.balance = balance;
    }
}
