package com.payhub.mobilemoney.mtn.mtnModels;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "accountholderbasicinfo")
public class AccountHolderBasicInfo {
    private String msisdn;
    private String firstname;
    private String surname;
    private String profilename;
    private String internalidentity;
    private List<DefaultFri> defaultfris;
    private String loyaltypointsaccountfri;
    private Acceptedtc acceptedtc;
    private Accountholderstatus accountholderstatus;
    private String bankdomainname;
    private boolean hasparent;
    private Languagecode languagecode;

    public AccountHolderBasicInfo() {
    }

    public String getMsisdn() {
        return msisdn;
    }

    @XmlElement(name = "msisdn")
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getFirstname() {
        return firstname;
    }

    @XmlElement(name = "firstname")
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    @XmlElement(name = "surname")
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getProfilename() {
        return profilename;
    }

    @XmlElement(name = "profilename")
    public void setProfilename(String profilename) {
        this.profilename = profilename;
    }

    public String getInternalidentity() {
        return internalidentity;
    }

    @XmlElement(name = "internalidentity")
    public void setInternalidentity(String internalidentity) {
        this.internalidentity = internalidentity;
    }

    public List<DefaultFri> getDefaultfris() {
        return defaultfris;
    }

    @XmlElement(name = "defaultfris")
    public void setDefaultfris(List<DefaultFri> defaultfris) {
        this.defaultfris = defaultfris;
    }

    public String getLoyaltypointsaccountfri() {
        return loyaltypointsaccountfri;
    }

    @XmlElement(name = "loyaltypointsaccountfri")
    public void setLoyaltypointsaccountfri(String loyaltypointsaccountfri) {
        this.loyaltypointsaccountfri = loyaltypointsaccountfri;
    }

    public Acceptedtc getAcceptedtc() {
        return acceptedtc;
    }

    @XmlElement(name = "acceptedtc")
    public void setAcceptedtc(Acceptedtc acceptedtc) {
        this.acceptedtc = acceptedtc;
    }

    public Accountholderstatus getAccountholderstatus() {
        return accountholderstatus;
    }

    @XmlElement(name = "accountholderstatus")
    public void setAccountholderstatus(Accountholderstatus accountholderstatus) {
        this.accountholderstatus = accountholderstatus;
    }

    public String getBankdomainname() {
        return bankdomainname;
    }

    @XmlElement(name = "bankdomainname")
    public void setBankdomainname(String bankdomainname) {
        this.bankdomainname = bankdomainname;
    }

    public boolean isHasparent() {
        return hasparent;
    }

    @XmlElement(name = "hasparent")
    public void setHasparent(boolean hasparent) {
        this.hasparent = hasparent;
    }

    public Languagecode getLanguagecode() {
        return languagecode;
    }

    @XmlElement(name = "languagecode")
    public void setLanguagecode(Languagecode languagecode) {
        this.languagecode = languagecode;
    }
}
