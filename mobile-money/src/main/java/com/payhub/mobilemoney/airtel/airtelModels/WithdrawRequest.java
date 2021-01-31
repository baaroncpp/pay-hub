package com.payhub.mobilemoney.airtel.airtelModels;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "COMMAND")
public class WithdrawRequest {
    private String msisdn;//<MSISDN>753169326</MSISDN>
    private String pin;//<PIN></PIN>
    private String msisdn2;//<MSISDN2>702814730</MSISDN2>
    private int payId;//<PAYID>12</PAYID>
    private int provider2;//<PROVIDER2>101</PROVIDER2>
    private int provider;//<PROVIDER>101</PROVIDER>
    private double amount;//<AMOUNT>2000</AMOUNT>
    private String otp;//<OTP>166157</OTP>
    private String type;//<TYPE>CASHOUTPAS</TYPE>
    private int payId2;//<PAYID2>12</PAYID2>
    private String trId;//<TRID>10031757598</TRID>
    private String isAmountCheckRequired;//<ISAMOUNTCHECKREQUIRED>Y</ISAMOUNTCHECKREQUIRED>

    public WithdrawRequest() {
    }

    public String getMsisdn() {
        return msisdn;
    }

    @XmlElement(name = "MSISDN")
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getPin() {
        return pin;
    }

    @XmlElement(name = "PIN")
    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getMsisdn2() {
        return msisdn2;
    }

    @XmlElement(name = "MSISDN2")
    public void setMsisdn2(String msisdn2) {
        this.msisdn2 = msisdn2;
    }

    public int getPayId() {
        return payId;
    }

    @XmlElement(name = "PAYID")
    public void setPayId(int payId) {
        this.payId = payId;
    }

    public int getProvider2() {
        return provider2;
    }

    @XmlElement(name = "PROVIDER2")
    public void setProvider2(int provider2) {
        this.provider2 = provider2;
    }

    public int getProvider() {
        return provider;
    }

    @XmlElement(name = "PROVIDER")
    public void setProvider(int provider) {
        this.provider = provider;
    }

    public double getAmount() {
        return amount;
    }

    @XmlElement(name = "AMOUNT")
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getOtp() {
        return otp;
    }

    @XmlElement(name = "OTP")
    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getType() {
        return type;
    }

    @XmlElement(name = "TYPE")
    public void setType(String type) {
        this.type = type;
    }

    public int getPayId2() {
        return payId2;
    }

    @XmlElement(name = "PAYID2")
    public void setPayId2(int payId2) {
        this.payId2 = payId2;
    }

    public String getTrId() {
        return trId;
    }

    @XmlElement(name = "TRID")
    public void setTrId(String trId) {
        this.trId = trId;
    }

    public String getIsAmountCheckRequired() {
        return isAmountCheckRequired;
    }

    @XmlElement(name = "ISAMOUNTCHECKREQUIRED")
    public void setIsAmountCheckRequired(String isAmountCheckRequired) {
        this.isAmountCheckRequired = isAmountCheckRequired;
    }
}
