package com.payhub.mobilemoney.mtn.mtnModels;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "receiverinfo")
public class RecieverInfo {
    private String fri;

    public RecieverInfo() {
    }

    public String getFri() {
        return fri;
    }

    @XmlElement(name = "fri")
    public void setFri(String fri) {
        this.fri = fri;
    }
}
