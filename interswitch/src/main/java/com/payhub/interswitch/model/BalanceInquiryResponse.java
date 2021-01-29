package com.payhub.interswitch.model;

public class BalanceInquiryResponse {
	private String terminalId;
	private String responseMessage;
	private String responseCode;

	public BalanceInquiryResponse() {
	}

	public BalanceInquiryResponse(String terminalId, String responseMessage, String responseCode) {
		this.terminalId = terminalId;
		this.responseMessage = responseMessage;
		this.responseCode = responseCode;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
}
