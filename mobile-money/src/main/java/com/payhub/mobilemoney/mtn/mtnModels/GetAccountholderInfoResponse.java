package com.payhub.mobilemoney.mtn.mtnModels;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "getaccountholderinforesponse")
public class GetAccountholderInfoResponse {
    private AccountHolderBasicInfo accountholderbasicinfo;

    public GetAccountholderInfoResponse() {
    }

    public AccountHolderBasicInfo getAccountholderbasicinfo() {
        return accountholderbasicinfo;
    }

    @XmlElement(name = "accountholderbasicinfo")
    public void setAccountholderbasicinfo(AccountHolderBasicInfo accountholderbasicinfo) {
        this.accountholderbasicinfo = accountholderbasicinfo;
    }
}
