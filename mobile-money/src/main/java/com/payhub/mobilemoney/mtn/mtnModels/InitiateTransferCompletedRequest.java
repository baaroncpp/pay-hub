package com.payhub.mobilemoney.mtn.mtnModels;

import com.payhub.mobilemoney.mtn.constants.Status;
import com.payhub.mobilemoney.mtn.mtnModels.RecieverInfo;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "ns0:initiatetransfercompletedrequest")
@XmlType(propOrder = {
        "financialtransactionid",
        "externaltransactionid",
        "receiverinfo",
        "status",
        "communicationchannel"
})
@Data
public class InitiateTransferCompletedRequest {
    private String financialtransactionid;
    private String externaltransactionid;
    private RecieverInfo recieverinfo;
    private Status status;
    private String communicationchannel;

    public InitiateTransferCompletedRequest() {
    }

    public String getFinancialtransactionid() {
        return financialtransactionid;
    }

    @XmlElement(name = "inancialtransactionid")
    public void setFinancialtransactionid(String financialtransactionid) {
        this.financialtransactionid = financialtransactionid;
    }

    public String getExternaltransactionid() {
        return externaltransactionid;
    }

    @XmlElement(name = "externaltransactionid")
    public void setExternaltransactionid(String externaltransactionid) {
        this.externaltransactionid = externaltransactionid;
    }

    public RecieverInfo getRecieverinfo() {
        return recieverinfo;
    }

    @XmlElement(name = "receiverinfo")
    public void setRecieverinfo(RecieverInfo recieverinfo) {
        this.recieverinfo = recieverinfo;
    }

    public Status getStatus() {
        return status;
    }

    @XmlElement(name = "status")
    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCommunicationchannel() {
        return communicationchannel;
    }

    @XmlElement(name = "ommunicationchannel")
    public void setCommunicationchannel(String communicationchannel) {
        this.communicationchannel = communicationchannel;
    }
}

//response 200
