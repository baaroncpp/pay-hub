package com.payhub.mobilemoney.mtn.mtnModels;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "account")
@XmlType(propOrder = {
        "fri",
        "accountstatus",
        "accounttype",
        "profilename",
        "referenceprofilename",
        "balance",
        "committedbalance",
        "totalreservation",
        "totalpositivereservation",
        "bankdomainname"
})
public class Account {
    private String fri;
    private String accountstatus;
    private Accounttype accounttype;
    private String profilename;
    private String referenceprofilename;
    private Account balance;
    private Account committedbalance;
    private Amount totalreservation;
    private Amount totalpositivereservation;
    private String bankdomainname;
}
