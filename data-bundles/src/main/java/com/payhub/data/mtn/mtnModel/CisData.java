package com.payhub.data.mtn.mtnModel;

import java.util.Currency;

public class CisData {
    private String id;
    //private String name;
    private Currency currency;
    private double amount;
    private String bundleCategory;
    private String bundleType;
    private String bundleValidity;

    public CisData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /*public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }*/

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBundleCategory() {
        return bundleCategory;
    }

    public void setBundleCategory(String bundleCategory) {
        this.bundleCategory = bundleCategory;
    }

    public String getBundleType() {
        return bundleType;
    }

    public void setBundleType(String bundleType) {
        this.bundleType = bundleType;
    }

    public String getBundleValidity() {
        return bundleValidity;
    }

    public void setBundleValidity(String bundleValidity) {
        this.bundleValidity = bundleValidity;
    }
}
