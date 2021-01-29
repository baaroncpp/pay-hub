package com.payhub.data.mtn.entity;

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
    private Long amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdon", nullable = false)
    private Date createdOn;

    @Column(name = "customernumber", nullable = false)
    private String customerNumber;

    @Column(name = "status", nullable = false)
    private BundlePaymentStatus status;
}
