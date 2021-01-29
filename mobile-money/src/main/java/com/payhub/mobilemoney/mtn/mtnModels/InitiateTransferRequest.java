package com.payhub.mobilemoney.mtn.mtnModels;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "ns0:initiatetransferrequest")
@XmlType(propOrder = {"accountholderidentity", "amount", "externaltransactionid", "message"})
public class InitiateTransferRequest {
    private String accountholderidentity;
    private Amount amount;
    private String message;
    private String externaltransactionid;

    public InitiateTransferRequest() {
    }

    public String getAccountholderidentity() {
        return accountholderidentity;
    }

    @XmlElement(name = "accountholderidentity")
    public void setAccountholderidentity(String accountholderidentity) {
        this.accountholderidentity = accountholderidentity;
    }

    public Amount getAmount() {
        return amount;
    }

    @XmlElement(name = "amount")
    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    @XmlElement(name = "message")
    public void setMessage(String message) {
        this.message = message;
    }

    public String getExternaltransactionid() {
        return externaltransactionid;
    }

    @XmlElement(name = "externaltransactionid")
    public void setExternaltransactionid(String externaltransactionid) {
        this.externaltransactionid = externaltransactionid;
    }
}
