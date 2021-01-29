package com.payhub.mobilemoney.mtn.mtnModels;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "balance")
class Balance{
    private Amount amount;

    public Balance() {
    }

    public Amount getAmount() {
        return amount;
    }

    @XmlElement(name = "amount")
    public void setAmount(Amount amount) {
        this.amount = amount;
    }
}
