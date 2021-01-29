package com.payhub.mobilemoney.mtn.mtnModels;

import com.payhub.mobilemoney.mtn.constants.Status;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "gettransactionstatusresponse")
public class GetTransactionStatusResponse {
    private String financialtransactionid;
    private Status status;
}
