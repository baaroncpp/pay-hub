package com.payhub.mobilemoney.mtn.mtnModels;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ns0:getaccountholderinforequest")
public class GetAccountholderInfoRequest {
    private String identity;

    public GetAccountholderInfoRequest() {
    }

    public String getIdentity() {
        return identity;
    }

    @XmlElement(name = "identity")
    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
