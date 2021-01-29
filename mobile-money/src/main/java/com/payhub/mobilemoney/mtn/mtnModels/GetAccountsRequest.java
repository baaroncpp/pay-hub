package com.payvault.mobilemoney.mtn.mtnModels;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "getaccountsrequest")
@Data
public class GetAccountsRequest {
    private String identity;
}
