package com.payhub.data.mtn.entity.jpa;

import com.payhub.data.airtel.constant.BundlePaymentStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "t_mtn_bundle_payment", schema = "mtn_bundles")
public class MtnBundlePayment {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bundleid", nullable = false)
    private MtnBundle bundleId;

    @JoinColumn(name = "amount", nullable = false)
    private double amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdon", nullable = false)
    private Date createdOn;

    @Column(name = "customernumber", nullable = false)
    private String customerNumber;

    @Column(name = "status", nullable = false)
    private BundlePaymentStatus status;

    @Column(name = "transactioind", nullable = false)
    private String transactionId;

    @Column(name = "description", nullable = false)
    private String description;

    public MtnBundlePayment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MtnBundle getBundleId() {
        return bundleId;
    }

    public void setBundleId(MtnBundle bundleId) {
        this.bundleId = bundleId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public BundlePaymentStatus getStatus() {
        return status;
    }

    public void setStatus(BundlePaymentStatus status) {
        this.status = status;
    }
}
