package com.payhub.data.mtn.service;

import com.payhub.data.mtn.constants.BundleCategory;
import com.payhub.data.mtn.entity.jpa.MtnBundle;
import com.payhub.data.mtn.models.MtnBundleModel;

import java.util.List;

public interface MtnBundleService {
    MtnBundle addMtnBundle(MtnBundleModel mtnBundleModel);
    MtnBundle getMtnBundleBy(String id);
    MtnBundle getMtnBundleByMtnBundleId(String mtnBundleId);//mtnBudleId is bundle name in Payhub
    List<MtnBundle> getAllBundleByCategory(BundleCategory bundleCategory);
    List<MtnBundle> getAllMtnBundles();
}
