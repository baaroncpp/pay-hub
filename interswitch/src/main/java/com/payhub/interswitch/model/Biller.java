package com.payhub.interswitch.model;

public class Biller {
	
    private String categoryid;
    private String categoryname;
    private String categorydescription;
    private String billerid;
    private String billername;
    private String paydirectProductId;
    private String paydirectInstitutionId;
    private String narration;
    private String shortName;
    private String surcharge;
    private String currencyCode;
    private String amountType;
    private Object pageFlowInfo;
    private String currencySymbol;
    private String logoUrl;
    private String type;

    public Biller() {
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getCategorydescription() {
        return categorydescription;
    }

    public void setCategorydescription(String categorydescription) {
        this.categorydescription = categorydescription;
    }

    public String getBillerid() {
        return billerid;
    }

    public void setBillerid(String billerid) {
        this.billerid = billerid;
    }

    public String getBillername() {
        return billername;
    }

    public void setBillername(String billername) {
        this.billername = billername;
    }

    public String getPaydirectProductId() {
        return paydirectProductId;
    }

    public void setPaydirectProductId(String paydirectProductId) {
        this.paydirectProductId = paydirectProductId;
    }

    public String getPaydirectInstitutionId() {
        return paydirectInstitutionId;
    }

    public void setPaydirectInstitutionId(String paydirectInstitutionId) {
        this.paydirectInstitutionId = paydirectInstitutionId;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(String surcharge) {
        this.surcharge = surcharge;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public Object getPageFlowInfo() {
        return pageFlowInfo;
    }

    public void setPageFlowInfo(Object pageFlowInfo) {
        this.pageFlowInfo = pageFlowInfo;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
