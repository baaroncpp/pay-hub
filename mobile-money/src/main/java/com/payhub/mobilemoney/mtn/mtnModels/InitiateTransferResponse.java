package com.payhub.mobilemoney.mtn.mtnModels;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "ns6:initiatetransferresponse")
@XmlType(propOrder = {"transactionid","approvalid"})
@Data
public class InitiateTransferResponse {
    private String transactionid;
    private String approvalid;

    public InitiateTransferResponse() {
    }

    public String getTransactionid() {
        return transactionid;
    }

    @XmlElement(name = "transactionid")
    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public String getApprovalid() {
        return approvalid;
    }

    @XmlElement(name = "approvalid")
    public void setApprovalid(String approvalid) {
        this.approvalid = approvalid;
    }
}
