package com.payhub.mobilemoney.airtel.airtelModels;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "COMMAND")
public class TransactionEnquiryResponse {
    private String type;
    private String txnId;
    private String extTrId;
    private String txnStatus;
    private String enqTxnStatus;
    private String message;
    private String trId;

    public TransactionEnquiryResponse() {
    }

    public String getType() {
        return type;
    }

    @XmlElement(name = "TYPE")
    public void setType(String type) {
        this.type = type;
    }

    public String getTxnId() {
        return txnId;
    }

    @XmlElement(name = "TXNID")
    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getExtTrId() {
        return extTrId;
    }

    @XmlElement(name = "EXTTRID")
    public void setExtTrId(String extTrId) {
        this.extTrId = extTrId;
    }

    public String getTxnStatus() {
        return txnStatus;
    }

    @XmlElement(name = "TXNSTATUS")
    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
    }

    public String getEnqTxnStatus() {
        return enqTxnStatus;
    }

    @XmlElement(name = "ENQTXNSTATUS")
    public void setEnqTxnStatus(String enqTxnStatus) {
        this.enqTxnStatus = enqTxnStatus;
    }

    public String getMessage() {
        return message;
    }

    @XmlElement(name = "MESSAGE")
    public void setMessage(String message) {
        this.message = message;
    }

    public String getTrId() {
        return trId;
    }

    @XmlElement(name = "TRID")
    public void setTrId(String trId) {
        this.trId = trId;
    }
}
