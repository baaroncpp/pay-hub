package com.payhub.interswitch.model;


public class InterswitchPayment {
	private Long amount;
	private Long surcharge;
	private String terminalId;
	private String requestReference;
	private String customerId;
	private String bankCbnCode;
	private String customerMobile;
	private String customerEmail;
	private String paymentCode;

	public InterswitchPayment() {
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getSurcharge() {
		return surcharge;
	}

	public void setSurcharge(Long surcharge) {
		this.surcharge = surcharge;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getRequestReference() {
		return requestReference;
	}

	public void setRequestReference(String requestReference) {
		this.requestReference = requestReference;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getBankCbnCode() {
		return bankCbnCode;
	}

	public void setBankCbnCode(String bankCbnCode) {
		this.bankCbnCode = bankCbnCode;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}
}
