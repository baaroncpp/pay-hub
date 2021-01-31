package com.payhub.mobilemoney.airtel.airtelModels;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "COMMAND")
public class PaymentRequest {
    private String type;
    private String mobTxNId;
    private double amount;
    private String reference;
    private String billerCode;
    private String username;
    private String password;
    private String msisdn;

    public PaymentRequest() {
    }

    public String getType() {
        return type;
    }

    @XmlElement(name = "TYPE")
    public void setType(String type) {
        this.type = type;
    }

    public String getMobTxNId() {
        return mobTxNId;
    }

    @XmlElement(name = "MOBTXNID")
    public void setMobTxNId(String mobTxNId) {
        this.mobTxNId = mobTxNId;
    }

    public double getAmount() {
        return amount;
    }

    @XmlElement(name = "AMOUNT")
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReference() {
        return reference;
    }

    @XmlElement(name = "REFERENCE")
    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getBillerCode() {
        return billerCode;
    }

    @XmlElement(name = "BILLERCODE")
    public void setBillerCode(String billerCode) {
        this.billerCode = billerCode;
    }

    public String getUsername() {
        return username;
    }

    @XmlElement(name = "USERNAME")
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @XmlElement(name = "PASSWORD")
    public void setPassword(String password) {
        this.password = password;
    }

    public String getMsisdn() {
        return msisdn;
    }

    @XmlElement(name = "MSISDN")
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }
}
