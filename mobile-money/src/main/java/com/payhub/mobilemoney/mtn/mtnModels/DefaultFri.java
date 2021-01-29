package com.payhub.mobilemoney.mtn.mtnModels;

import com.payhub.mobilemoney.mtn.constants.Currency;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "defaultfri")
public class DefaultFri {
    private String fri;
    private Currency currency;

    public DefaultFri() {
    }

    public String getFri() {
        return fri;
    }

    @XmlElement(name = "fri")
    public void setFri(String fri) {
        this.fri = fri;
    }

    public Currency getCurrency() {
        return currency;
    }

    @XmlElement(name = "fri")
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
