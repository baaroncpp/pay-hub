package com.payhub.data.mtn.models;

import com.payhub.data.mtn.constants.BundleCategory;

import java.util.Currency;

public class MtnBundlePriceModel {
    private String bundleId;
    private String name;
    private Currency currency;
    private double amount;
    private String bundleCategory;
    private String bundleValidity;

    public MtnBundlePriceModel() {
    }

    public String getBundleId() {
        return bundleId;
    }

    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getBundleValidity() {
        return bundleValidity;
    }

    public void setBundleValidity(String bundleValidity) {
        this.bundleValidity = bundleValidity;
    }
}
