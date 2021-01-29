package com.payhub.data.airtel.model;

import lombok.Data;

@Data
public class BundlePaymentModel {
    private String bundleid;
    private String customerNumber;

    public BundlePaymentModel() {
    }

    public String getBundleid() {
        return bundleid;
    }

    public void setBundleid(String bundleid) {
        this.bundleid = bundleid;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
}
