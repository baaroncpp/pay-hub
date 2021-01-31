package com.payhub.data.mtn.models;

import com.payhub.data.mtn.constants.BundleCategory;

public class MtnBundleModel {
    private String mtnBundleId;//MTN BUNDLE ID
    private BundleCategory category;
    private String description;

    public MtnBundleModel() {
    }

    public String getMtnBundleId() {
        return mtnBundleId;
    }

    public void setMtnBundleId(String mtnBundleId) {
        this.mtnBundleId = mtnBundleId;
    }

    public BundleCategory getCategory() {
        return category;
    }

    public void setCategory(BundleCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
