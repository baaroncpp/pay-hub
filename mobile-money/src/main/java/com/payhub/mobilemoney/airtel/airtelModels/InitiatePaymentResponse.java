package com.payhub.mobilemoney.airtel.airtelModels;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "COMMAND")
public class InitiatePaymentResponse {
    private String partTxnId;
    private String type;
    private String mobTxnId;
    private int txnStatus;
    private String mesaage;

    public InitiatePaymentResponse() {
    }

    public String getPartTxnId() {
        return partTxnId;
    }

    @XmlElement(name = "PARTTXNID")
    public void setPartTxnId(String partTxnId) {
        this.partTxnId = partTxnId;
    }

    public String getType() {
        return type;
    }

    @XmlElement(name = "TYPE")
    public void setType(String type) {
        this.type = type;
    }

    public String getMobTxnId() {
        return mobTxnId;
    }

    @XmlElement(name = "MOBTXNID")
    public void setMobTxnId(String mobTxnId) {
        this.mobTxnId = mobTxnId;
    }

    public int getTxnStatus() {
        return txnStatus;
    }

    @XmlElement(name = "TXNSTATUS")
    public void setTxnStatus(int txnStatus) {
        this.txnStatus = txnStatus;
    }

    public String getMesaage() {
        return mesaage;
    }

    @XmlElement(name = "MESSAGE")
    public void setMesaage(String mesaage) {
        this.mesaage = mesaage;
    }
}