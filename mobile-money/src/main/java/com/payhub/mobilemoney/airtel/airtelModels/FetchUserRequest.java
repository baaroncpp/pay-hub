package com.payhub.mobilemoney.airtel.airtelModels;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "COMMAND")
public class FetchUserRequest {
    private String type;//TYPE>CKYCREQ</TYPE>
    private int sndInstruction;//<SNDINSTRUMENT>12</SNDINSTRUMENT>
    private String msisdn;//<MSISDN>707444219</MSISDN>
    private int payId;//<PAYID>12</PAYID>
    private int sndProvider;//<SNDPROVIDER>101</SNDPROVIDER>
    private String language;//<language>en</language>
    private int rcvInstrument;//<RCVINSTRUMENT>12</RCVINSTRUMENT>
    private int language2;//<LANGUAGE2>1</LANGUAGE2>
    private int payId2;//<PAYID2>12</PAYID2>
    private int language1;//<LANGUAGE1>1</LANGUAGE1>
    private int payId1;//<PAYID1>12</PAYID1>
    private int provider;//<PROVIDER>101</PROVIDER>
    private int provider2;//<PROVIDER2>101</PROVIDER2>
    private String extReq;//<extreq>Y</extreq>
    private String extTrid;//<exttrid></exttrid>
    private String interfaceId;//<interfaceId>CORETXN</interfaceId>
    private String username;//<username></username>
    private String password;//<password></password>
    private String paymentMode;//<PAYMENTMODE>$PAYMENTMODE</PAYMENTMODE>

    public FetchUserRequest() {
    }

    public String getType() {
        return type;
    }

    @XmlElement(name = "TYPE")
    public void setType(String type) {
        this.type = type;
    }

    public int getSndInstruction() {
        return sndInstruction;
    }

    @XmlElement(name = "SNDINSTRUMENT")
    public void setSndInstruction(int sndInstruction) {
        this.sndInstruction = sndInstruction;
    }

    public String getMsisdn() {
        return msisdn;
    }

    @XmlElement(name = "MSISDN")
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public int getPayId() {
        return payId;
    }

    @XmlElement(name = "PAYID")
    public void setPayId(int payId) {
        this.payId = payId;
    }

    public int getSndProvider() {
        return sndProvider;
    }

    @XmlElement(name = "SNDPROVIDER")
    public void setSndProvider(int sndProvider) {
        this.sndProvider = sndProvider;
    }

    public String getLanguage() {
        return language;
    }

    @XmlElement(name = "language")
    public void setLanguage(String language) {
        this.language = language;
    }

    public int getRcvInstrument() {
        return rcvInstrument;
    }

    @XmlElement(name = "RCVINSTRUMENT")
    public void setRcvInstrument(int rcvInstrument) {
        this.rcvInstrument = rcvInstrument;
    }

    public int getLanguage2() {
        return language2;
    }

    @XmlElement(name = "LANGUAGE2")
    public void setLanguage2(int language2) {
        this.language2 = language2;
    }

    public int getPayId2() {
        return payId2;
    }

    @XmlElement(name = "PAYID2")
    public void setPayId2(int payId2) {
        this.payId2 = payId2;
    }

    public int getLanguage1() {
        return language1;
    }

    @XmlElement(name = "LANGUAGE1")
    public void setLanguage1(int language1) {
        this.language1 = language1;
    }

    public int getPayId1() {
        return payId1;
    }

    @XmlElement(name = "PAYID1")
    public void setPayId1(int payId1) {
        this.payId1 = payId1;
    }

    public int getProvider() {
        return provider;
    }

    @XmlElement(name = "PROVIDER")
    public void setProvider(int provider) {
        this.provider = provider;
    }

    public int getProvider2() {
        return provider2;
    }

    @XmlElement(name = "PROVIDER2")
    public void setProvider2(int provider2) {
        this.provider2 = provider2;
    }

    public String getExtReq() {
        return extReq;
    }

    @XmlElement(name = "extreq")
    public void setExtReq(String extReq) {
        this.extReq = extReq;
    }

    public String getExtTrid() {
        return extTrid;
    }

    @XmlElement(name = "exttrid")
    public void setExtTrid(String extTrid) {
        this.extTrid = extTrid;
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

    @XmlElement(name = "username")
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @XmlElement(name = "password")
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    @XmlElement(name = "PAYMENTMODE")
    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
}
