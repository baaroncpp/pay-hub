package com.payhub.data.mtn.constants;

public enum BundleCategory {
    DATA_BUNDLES("Data Bundles"),
    VOICE_BUNDLES("Voice Bundles");

    String message;

    BundleCategory(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
