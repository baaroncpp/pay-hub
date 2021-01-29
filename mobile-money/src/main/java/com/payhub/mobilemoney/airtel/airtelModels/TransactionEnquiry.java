package com.payhub.mobilemoney.airtel.airtelModels;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "COMMAND")
public class TransactionEnquiry {
    private String type;
    private String interfaceId;
    private String extTriId;
    private String trId;

    public TransactionEnquiry() {
    }

    public String getType() {
        return type;
    }

    @XmlElement(name = "TYPE")
    public void setType(String type) {
        this.type = type;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    @XmlElement(name = "interfaceId")
    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getExtTriId() {
        return extTriId;
    }

    @XmlElement(name = "EXTTRID")
    public void setExtTriId(String extTriId) {
        this.extTriId = extTriId;
    }

    public String getTrId() {
        return trId;
    }

    @XmlElement(name = "TRID")
    public void setTrId(String trId) {
        this.trId = trId;
    }
}
