package com.payhub.data.airtel.repository;

import com.payhub.data.airtel.entity.Bundle;
import com.payhub.data.airtel.entity.BundlePayment;
import com.payhub.data.airtel.entity.BundleReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BundleReceiptRepository extends JpaRepository<BundleReceipt, String> {

    Optional<BundleReceipt> findBybundlePayment(BundlePayment bundlePayment);
}
