package com.payhub.mobilemoney.airtel.airtelModels;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "COMMAND")
public class DepositResponse {
    private String type;
    private String txnId;
    private int txnStatus;
    private String message;
    private String merchantTxnId;
    private String extTrId;
    private String trId;

    public DepositResponse() {
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

    public int getTxnStatus() {
        return txnStatus;
    }

    @XmlElement(name = "TXNSTATUS")
    public void setTxnStatus(int txnStatus) {
        this.txnStatus = txnStatus;
    }

    public String getMessage() {
        return message;
    }

    @XmlElement(name = "MESSAGE")
    public void setMessage(String message) {
        this.message = message;
    }

    public String getMerchantTxnId() {
        return merchantTxnId;
    }

    @XmlElement(name = "MERCHANT_TXN_ID")
    public void setMerchantTxnId(String merchantTxnId) {
        this.merchantTxnId = merchantTxnId;
    }

    public String getExtTrId() {
        return extTrId;
    }

    @XmlElement(name = "EXTTRID")
    public void setExtTrId(String extTrId) {
        this.extTrId = extTrId;
    }

    public String getTrId() {
        return trId;
    }

    @XmlElement(name = "TRID")
    public void setTrId(String trId) {
        this.trId = trId;
    }
}
