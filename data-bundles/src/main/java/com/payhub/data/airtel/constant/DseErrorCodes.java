package com.payhub.data.airtel.constant;

public enum DseErrorCodes {
	
	SUCCESS(200, "Success"),
	CREATED(201, "Created"),
	SUCCESS_2(202, "Success"),
	BAD_REQUEST(400, "Bad Request"),
	UNAUTHORIZED(401, "Unauthorized"),
	FORBIDDEN(403, "Forbidden"),
	NOT_FOUND(404, "Not Found"),
	NOT_ALLOWED(405, "Method Not Allowed");
	
	int code;
	String message;
	
	DseErrorCodes(int code, String message){
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
