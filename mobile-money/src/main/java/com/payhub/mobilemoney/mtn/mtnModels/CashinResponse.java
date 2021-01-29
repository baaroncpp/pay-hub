package com.payhub.mobilemoney.mtn.mtnModels;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "ns5:cashinresponse")
@XmlType(propOrder = {
        "fee",
        "senderbalanceafter",
        "receiverbalanceafter",
        "receiverpromotion",
        "offeridentities",
        "financialtransactionid"
})
@Data
public class CashinResponse {
    private Amount fee;
    private Amount senderbalanceafter;
    private Amount receiverbalanceafter;
    private Amount receiverpromotion;
    private String financialtransactionid;

    public CashinResponse() {
    }

    public Amount getFee() {
        return fee;
    }

    @XmlElement(name = "fee")
    public void setFee(Amount fee) {
        this.fee = fee;
    }

    public Amount getSenderbalanceafter() {
        return senderbalanceafter;
    }

    @XmlElement(name = "senderbalanceafter")
    public void setSenderbalanceafter(Amount senderbalanceafter) {
        this.senderbalanceafter = senderbalanceafter;
    }

    public Amount getReceiverbalanceafter() {
        return receiverbalanceafter;
    }

    @XmlElement(name = "receiverbalanceafter")
    public void setReceiverbalanceafter(Amount receiverbalanceafter) {
        this.receiverbalanceafter = receiverbalanceafter;
    }

    public Amount getReceiverpromotion() {
        return receiverpromotion;
    }

    @XmlElement(name = "receiverpromotion")
    public void setReceiverpromotion(Amount receiverpromotion) {
        this.receiverpromotion = receiverpromotion;
    }

    public String getFinancialtransactionid() {
        return financialtransactionid;
    }

    @XmlElement(name = "financialtransactionid")
    public void setFinancialtransactionid(String financialtransactionid) {
        this.financialtransactionid = financialtransactionid;
    }
}