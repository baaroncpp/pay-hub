package com.payhub.data.mtn.constants;

public enum ResponseStatusCode {

    CREATED(0, 201, "Bundle Activation Successful"),
    NOT_FOUND(3004, 404, "Product not valid"),
    INVALID_TRANSACTION_ID(3005, 0, "Transaction Id does not exist"),
    FORBIDDEN(6001, 403, "Insufficient Funds"),
    SYSTEM_ERROR(3001, 500, "System error OR Generic Error");

    int statusCode;
    int httpCode;
    String message;

    ResponseStatusCode(int statusCode, int httpCode, String message) {
        this.statusCode = statusCode;
        this.httpCode = httpCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
