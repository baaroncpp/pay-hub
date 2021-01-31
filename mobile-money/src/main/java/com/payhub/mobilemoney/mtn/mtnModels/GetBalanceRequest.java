package com.payhub.mobilemoney.mtn.mtnModels;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ns2:getbalancerequest")
public class GetBalanceRequest {
    private String fri;

    public GetBalanceRequest() {
    }

    public String getFri() {
        return fri;
    }

    @XmlElement(name = "fri")
    public void setFri(String fri) {
        this.fri = fri;
    }
}
