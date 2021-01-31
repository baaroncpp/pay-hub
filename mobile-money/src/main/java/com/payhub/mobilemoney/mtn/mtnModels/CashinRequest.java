package com.payhub.mobilemoney.mtn.mtnModels;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "ns0:cashinrequest")
@XmlType(propOrder = {
        "sendingfri",
        "receivingfri",
        "amount",
        "sendernote",
        "receivermessage"
})
public class CashinRequest {
    private String sendingfri;
    private String receivingfri;
    private Amount amount;
    private String sendernote;
    private String receivermessage;

    public CashinRequest() {
    }

    public String getSendingfri() {
        return sendingfri;
    }

    @XmlElement(name = "sendingfri")
    public void setSendingfri(String sendingfri) {
        this.sendingfri = sendingfri;
    }

    public String getReceivingfri() {
        return receivingfri;
    }

    @XmlElement(name = "receivingfri")
    public void setReceivingfri(String receivingfri) {
        this.receivingfri = receivingfri;
    }

    public Amount getAmount() {
        return amount;
    }

    @XmlElement(name = "amount")
    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getSendernote() {
        return sendernote;
    }

    @XmlElement(name = "sendernote")
    public void setSendernote(String sendernote) {
        this.sendernote = sendernote;
    }

    public String getReceivermessage() {
        return receivermessage;
    }

    @XmlElement(name = "receivermessage")
    public void setReceivermessage(String receivermessage) {
        this.receivermessage = receivermessage;
    }
}
