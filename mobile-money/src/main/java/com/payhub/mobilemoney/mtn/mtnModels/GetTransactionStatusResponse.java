package com.payvault.mobilemoney.mtn.mtnModels;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "gettransactionstatusresponse")
public class GetTransactionStatusResponse {
    private String financialtransactionid;
    private Status status;
}
