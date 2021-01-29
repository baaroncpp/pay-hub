package com.payhub.data.mtn.service;

import com.payhub.data.mtn.entity.jpa.MtnBundlePayment;
import com.payhub.data.mtn.models.MtnBundlePaymentModel;
import com.payhub.data.mtn.models.MtnBundlePriceModel;
import com.payhub.data.mtn.models.TransactionStatusModel;

public interface MtnBundlePaymentService {

    MtnBundlePriceModel getBundlePrice(String bundleId, String msisdn);
    MtnBundlePayment activateBundle(BuyBundle buyBundle);
    TransactionStatusModel getBundlePaymentStatus(String transactionId);
}
