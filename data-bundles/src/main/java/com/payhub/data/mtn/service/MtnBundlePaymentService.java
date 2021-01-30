package com.payhub.data.mtn.service;

import com.payhub.data.mtn.entity.jpa.MtnBundlePayment;
import com.payhub.data.mtn.models.BuyBundle;
import com.payhub.data.mtn.models.MtnBundlePriceModel;
import com.payhub.data.mtn.models.MtnTransactionStatusModel;

public interface MtnBundlePaymentService {

    MtnBundlePriceModel getBundlePrice(String bundleId, String msisdn);
    MtnBundlePayment activateBundle(BuyBundle buyBundle);
    MtnTransactionStatusModel getBundlePaymentStatus(String transactionId);
}
