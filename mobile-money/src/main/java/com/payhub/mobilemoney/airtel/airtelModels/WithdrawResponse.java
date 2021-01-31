package com.payhub.mobilemoney.airtel.airtelModels;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "COMMAND")
public class WithdrawResponse {
    private String type;
    private String txnStatus;
    private String txnId;
    private String message;

    public WithdrawResponse() {
    }

    public String getType() {
        return type;
    }

    @XmlElement(name = "TYPE")
    public void setType(String type) {
        this.type = type;
    }

    public String getTxnStatus() {
        return txnStatus;
    }

    @XmlElement(name = "TXNSTATUS")
    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
    }

    public String getTxnId() {
        return txnId;
    }

    @XmlElement(name = "TXNID")
    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getMessage() {
        return message;
    }

    @XmlElement(name = "MESSAGE")
    public void setMessage(String message) {
        this.message = message;
    }
}
