package com.payhub.notification.model;


public class SmsResponse {
    private String status;
    private String reference;

    public SmsResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
