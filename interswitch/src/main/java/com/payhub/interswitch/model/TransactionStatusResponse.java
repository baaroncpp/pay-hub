package com.payhub.interswitch.model;

public class TransactionStatusResponse {
	
	private String reponseMessage;
	private String responseCode;
	private String customer;
	private String rechargePIN;
	private String transferCode;
	private String requestReference;
	private String transactionRef;

	public TransactionStatusResponse() {
	}

	public String getReponseMessage() {
		return reponseMessage;
	}

	public void setReponseMessage(String reponseMessage) {
		this.reponseMessage = reponseMessage;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getRechargePIN() {
		return rechargePIN;
	}

	public void setRechargePIN(String rechargePIN) {
		this.rechargePIN = rechargePIN;
	}

	public String getTransferCode() {
		return transferCode;
	}

	public void setTransferCode(String transferCode) {
		this.transferCode = transferCode;
	}

	public String getRequestReference() {
		return requestReference;
	}

	public void setRequestReference(String requestReference) {
		this.requestReference = requestReference;
	}

	public String getTransactionRef() {
		return transactionRef;
	}

	public void setTransactionRef(String transactionRef) {
		this.transactionRef = transactionRef;
	}
}
