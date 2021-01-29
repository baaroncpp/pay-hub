package com.payhub.data.mtn.mtnModel;

public class ActivateBundleRequest {

    private String subscriptionId;
    private String subscriptionProviderId;
    private String subscriptionName;
    private String registrationChannel;
    private String subscriptionPaymentSource;
    private boolean sendSMSNotification;
    private String beneficiaryId;

    public ActivateBundleRequest() {
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriptionProviderId() {
        return subscriptionProviderId;
    }

    public void setSubscriptionProviderId(String subscriptionProviderId) {
        this.subscriptionProviderId = subscriptionProviderId;
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    public String getRegistrationChannel() {
        return registrationChannel;
    }

    public void setRegistrationChannel(String registrationChannel) {
        this.registrationChannel = registrationChannel;
    }

    public String getSubscriptionPaymentSource() {
        return subscriptionPaymentSource;
    }

    public void setSubscriptionPaymentSource(String subscriptionPaymentSource) {
        this.subscriptionPaymentSource = subscriptionPaymentSource;
    }

    public boolean isSendSMSNotification() {
        return sendSMSNotification;
    }

    public void setSendSMSNotification(boolean sendSMSNotification) {
        this.sendSMSNotification = sendSMSNotification;
    }

    public String getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }
}
