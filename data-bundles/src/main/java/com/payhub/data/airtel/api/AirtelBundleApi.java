package com.payhub.data.airtel.api;

import com.payhub.data.airtel.service.BundlePaymentService;
import com.payhub.data.airtel.service.BundleReceiptService;
import com.payhub.data.airtel.service.BundleService;
import org.springframework.beans.factory.annotation.Autowired;

public class AirtelBundleApi{

    private static BundleService bundleService;
    private static BundleReceiptService bundleReceiptService;
    private static BundlePaymentService bundlePaymentService;

    @Autowired
    public AirtelBundleApi(BundleService bundleService,
                                  BundleReceiptService bundleReceiptService,
                                  BundlePaymentService bundlePaymentService){
        AirtelBundleApi.bundlePaymentService = bundlePaymentService;
        AirtelBundleApi.bundleReceiptService = bundleReceiptService;
        AirtelBundleApi.bundleService = bundleService;
    }

    public static BundlePaymentService getBundlePaymentService() {
        return bundlePaymentService;
    }

    public static BundleReceiptService getBundleReceiptService() {
        return bundleReceiptService;
    }

    public static BundleService getBundleService() {
        return bundleService;
    }
}
