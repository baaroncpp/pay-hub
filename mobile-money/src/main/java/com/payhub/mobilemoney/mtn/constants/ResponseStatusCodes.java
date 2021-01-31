package com.payhub.mobilemoney.mtn.constants;

public enum ResponseStatusCodes {
    SUCCESS(200,"request completed successfully"),
    ACCEPTED(202, "accepted but processing has not been completed (pending)"),
    MOVED_TEMPORARILY(302, "request redirected"),
    FORBIDDEN(403, "Forbidden"),
    UNAUTHORIZED(401, "Unauthorized"),
    NOT_FOUND(404, "Not found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    int code;
    String message;

    ResponseStatusCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
