package com.payhub.data.mtn.mtnModel;

import javax.persistence.Id;

public class ActivateBundleResponseSuccess {
    private String statusCode;
    private String subscriptionId;
    private String subscriptionProviderId;
    private String subscriptionName;
    private String subscriptionDescription;
    private String subscriptionStatus;
    private String subscriptionType;
    private String subscriptionLength;
    private String registrationChannel;
    private String startDate;
    private String endDate;
    private String email;
    private double amountCharged;
    private String subscriptionPaymentSource;
    private boolean sendSMSNotification;
    private String beneficiaryId;
    private boolean autoRenew;

    @Id
    private String transactionId;
    private String statusDescription;

    public ActivateBundleResponseSuccess() {
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
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

    public String getSubscriptionDescription() {
        return subscriptionDescription;
    }

    public void setSubscriptionDescription(String subscriptionDescription) {
        this.subscriptionDescription = subscriptionDescription;
    }

    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(String subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getSubscriptionLength() {
        return subscriptionLength;
    }

    public void setSubscriptionLength(String subscriptionLength) {
        this.subscriptionLength = subscriptionLength;
    }

    public String getRegistrationChannel() {
        return registrationChannel;
    }

    public void setRegistrationChannel(String registrationChannel) {
        this.registrationChannel = registrationChannel;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getAmountCharged() {
        return amountCharged;
    }

    public void setAmountCharged(double amountCharged) {
        this.amountCharged = amountCharged;
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

    public boolean isAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(boolean autoRenew) {
        this.autoRenew = autoRenew;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
}
