package com.payhub.data.airtel.airtelapi;

import com.payhub.data.airtel.service.BundlePaymentService;
import com.payhub.data.airtel.service.BundleReceiptService;
import com.payhub.data.airtel.service.BundleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AirtelBundleServiceApi {

    @Autowired
    private BundlePaymentService bundlePaymentService;

    @Autowired
    private BundleReceiptService bundleReceiptService;

    @Autowired
    private BundleService bundleService;

    public BundlePaymentService getBundlePaymentService() {
        return bundlePaymentService;
    }

    public BundleReceiptService getBundleReceiptService() {
        return bundleReceiptService;
    }

    public BundleService getBundleService() {
        return bundleService;
    }

}
