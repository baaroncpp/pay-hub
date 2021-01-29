package com.payhub.mobilemoney.mtn.mtnModels;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "getaccountsresponse")
public class GetAccountsResponse {
    List<com.payvault.mobilemoney.mtn.mtnModels.Account> accountslist;
}
