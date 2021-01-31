package com.payhub.mobilemoney.airtel.airtelModels;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "COMMAND")
public class DepositRequest {
    private String sndInstrument;
    private String msisdn;
    private String payId;
    private String sndProvider;
    private String language;
    private String rcvInstrument;
    private String language2;
    private String payId2;
    private String language1;
    private String provider1;//
    private String pin;
    private String provider2;
    private double amount;
    private String msisdn2;
    private String rcvProvider;
    private String isMerchantCashin;
    private String merchantTxnId;
    private int provider;
    private int bProvider;
    private boolean pinCheck;
    private String type;
    private String interfaceId;
    private String username;
    private String password;

    public DepositRequest() {
    }

    public String getSndInstrument() {
        return sndInstrument;
    }

    @XmlElement(name = "SNDINSTRUMENT")
    public void setSndInstrument(String sndInstrument) {
        this.sndInstrument = sndInstrument;
    }

    public String getMsisdn() {
        return msisdn;
    }

    @XmlElement(name = "MSISDN")
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getPayId() {
        return payId;
    }

    @XmlElement(name = "PAYID")
    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getSndProvider() {
        return sndProvider;
    }

    @XmlElement(name = "SNDPROVIDER")
    public void setSndProvider(String sndProvider) {
        this.sndProvider = sndProvider;
    }

    public String getLanguage() {
        return language;
    }

    @XmlElement(name = "language")
    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRcvInstrument() {
        return rcvInstrument;
    }

    @XmlElement(name = "RCVINSTRUMENT")
    public void setRcvInstrument(String rcvInstrument) {
        this.rcvInstrument = rcvInstrument;
    }

    public String getLanguage2() {
        return language2;
    }

    @XmlElement(name = "LANGUAGE2")
    public void setLanguage2(String language2) {
        this.language2 = language2;
    }

    public String getPayId2() {
        return payId2;
    }

    @XmlElement(name = "PAYID2")
    public void setPayId2(String payId2) {
        this.payId2 = payId2;
    }

    public String getLanguage1() {
        return language1;
    }

    @XmlElement(name = "LANGUAGE1")
    public void setLanguage1(String language1) {
        this.language1 = language1;
    }

    public String getProvider1() {
        return provider1;
    }

    @XmlElement(name = "PROVIDER1")
    public void setProvider1(String provider1) {
        this.provider1 = provider1;
    }

    public String getPin() {
        return pin;
    }

    @XmlElement(name = "PIN")
    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getProvider2() {
        return provider2;
    }

    @XmlElement(name = "PROVIDER2")
    public void setProvider2(String provider2) {
        this.provider2 = provider2;
    }

    public double getAmount() {
        return amount;
    }

    @XmlElement(name = "AMOUNT")
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMsisdn2() {
        return msisdn2;
    }

    @XmlElement(name = "MSISDN2")
    public void setMsisdn2(String msisdn2) {
        this.msisdn2 = msisdn2;
    }

    public String getRcvProvider() {
        return rcvProvider;
    }

    @XmlElement(name = "RCVPROVIDER")
    public void setRcvProvider(String rcvProvider) {
        this.rcvProvider = rcvProvider;
    }

    public String getIsMerchantCashin() {
        return isMerchantCashin;
    }

    @XmlElement(name = "IS_MERCHANT_CASHIN")
    public void setIsMerchantCashin(String isMerchantCashin) {
        this.isMerchantCashin = isMerchantCashin;
    }

    public String getMerchantTxnId() {
        return merchantTxnId;
    }

    @XmlElement(name = "MERCHANT_TXN_ID")
    public void setMerchantTxnId(String merchantTxnId) {
        this.merchantTxnId = merchantTxnId;
    }

    public int getProvider() {
        return provider;
    }

    @XmlElement(name = "PROVIDER")
    public void setProvider(int provider) {
        this.provider = provider;
    }

    public int getbProvider() {
        return bProvider;
    }

    @XmlElement(name = "BPROVIDER")
    public void setbProvider(int bProvider) {
        this.bProvider = bProvider;
    }

    public boolean isPinCheck() {
        return pinCheck;
    }

    @XmlElement(name = "PIN_CHECK")
    public void setPinCheck(boolean pinCheck) {
        this.pinCheck = pinCheck;
    }

    public String getType() {
        return type;
    }

    @XmlElement(name = "TYPE")
    public void setType(String type) {
        this.type = type;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    @XmlElement(name = "interfaceId")
    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
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

}
