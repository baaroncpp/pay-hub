package com.payhub.data.mtn.mtnModel;

public class ValidBundleData {
    private String customerId;
    private CisData data;
    private Link link_;

    public ValidBundleData() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public CisData getData() {
        return data;
    }

    public void setData(CisData data) {
        this.data = data;
    }

    public Link getLink_() {
        return link_;
    }

    public void setLink_(Link link_) {
        this.link_ = link_;
    }
}
