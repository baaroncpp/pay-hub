package com.payhub.data.mtn.models;

public class MtnTransactionStatusModel {
    private String paymentId;//bundle payment id payhub
    private String bundleName;//bundle id MTN side
    private String mtnTransactionId;
    private String customerMsisdn;
    private String subscriptionStatus;
    private boolean autoRenew;

    public MtnTransactionStatusModel() {
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    public String getMtnTransactionId() {
        return mtnTransactionId;
    }

    public void setMtnTransactionId(String mtnTransactionId) {
        this.mtnTransactionId = mtnTransactionId;
    }

    public String getCustomerMsisdn() {
        return customerMsisdn;
    }

    public void setCustomerMsisdn(String customerMsisdn) {
        this.customerMsisdn = customerMsisdn;
    }

    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(String subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public boolean isAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(boolean autoRenew) {
        this.autoRenew = autoRenew;
    }
}
