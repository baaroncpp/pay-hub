package com.payhub.data.mtn.mtnModel;

public class TransactionStatusResponse {
    private String customerId;
    private ServicesObj services;

    public TransactionStatusResponse() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public ServicesObj getServices() {
        return services;
    }

    public void setServices(ServicesObj services) {
        this.services = services;
    }
}
