package com.payhub.data.mtn.entity.jpa;

import com.payhub.data.airtel.constant.BundlePaymentStatus;
import com.payhub.data.airtel.entity.BundlePayment;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_mtn_bundle_receipt", schema = "airtel_data")
public class MtnBundleReceipt {

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
}
