package com.payhub.data.mtn.api;

import com.payhub.data.mtn.service.MtnBundlePaymentService;
import com.payhub.data.mtn.service.MtnBundleService;
import org.springframework.beans.factory.annotation.Autowired;

public class MtnBundlesApi {

    @Autowired
    MtnBundleService mtnBundleService;

    @Autowired
    MtnBundlePaymentService mtnBundlePaymentService;

    public MtnBundleService getMtnBundleService() {
        return mtnBundleService;
    }

    public MtnBundlePaymentService getMtnBundlePaymentService() {
        return mtnBundlePaymentService;
    }
}
