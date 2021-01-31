package com.payhub.data.airtel.model;

public class ErrorResponse {
	private String resultDesc;
	private String resultCode;

	public ErrorResponse() {
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
}
