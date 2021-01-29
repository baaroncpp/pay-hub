package com.payhub.data.airtel.service;

import com.payhub.data.airtel.entity.BundleReceipt;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BundleReceiptService {
    List<BundleReceipt> getBundleReceipt(Pageable pageable);
    BundleReceipt getBundleReceiptByBundlePaymentId(String bundlePaymentId);
}
