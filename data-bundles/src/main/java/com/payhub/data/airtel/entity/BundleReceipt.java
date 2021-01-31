package com.payhub.data.airtel.entity;

import com.payhub.data.airtel.constant.BundlePaymentStatus;

import javax.persistence.*;

@Entity
@Table(name = "t_bundle_receipt", schema = "airtel_data")
public class BundleReceipt {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bundlepaymentid", nullable = false)
    private BundlePayment bundlePayment;

    @Column(name = "responsedescription")
    private String responseDescription;

    @Column(name = "bundlepaymentstatus")
    private BundlePaymentStatus bundlePaymentStatus;

    public BundleReceipt() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BundlePayment getBundlePayment() {
        return bundlePayment;
    }

    public void setBundlePayment(BundlePayment bundlePayment) {
        this.bundlePayment = bundlePayment;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public BundlePaymentStatus getBundlePaymentStatus() {
        return bundlePaymentStatus;
    }

    public void setBundlePaymentStatus(BundlePaymentStatus bundlePaymentStatus) {
        this.bundlePaymentStatus = bundlePaymentStatus;
    }
}
