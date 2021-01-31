package com.payhub.data.mtn.repository;

import com.payhub.data.mtn.entity.jpa.MtnBundlePayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MtnBundlePaymentRepository extends JpaRepository<MtnBundlePayment, String> {
}
