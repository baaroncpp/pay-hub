package com.payhub.notification.model;

public class DeliveryReportModel {
    private String token;
    private String reference;

    public DeliveryReportModel() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
