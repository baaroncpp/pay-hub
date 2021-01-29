package com.payhub.interswitch.jpa.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_payment_notification",schema="interswitch")
public class PaymentNotification {
	
	@Column(name = "amount", nullable = false)
	private String amount;
	
	@Column(name = "surcharge", nullable = false)
	private String surcharge;

	@Transient
	private String terminalId;
	
	@Id
	@Column(name = "requestreference", unique = true, nullable = false)
	private String requestReference;
	
	@Column(name = "customerid", nullable = false)
	private String customerId;
	
	@Transient
	private String bankCbnCode;
	
	@Column(name = "customermobile")
	private String customerMobile;
	
	@Column(name = "transactionref", nullable = false)
	private String transactionRef;
	
	@Column(name = "customeremail")
	private String customerEmail;
	
	@Column(name = "paymentcode", nullable = false)
	private String paymentCode;
	
	@Column(name = "narration")
	private String Narration;
	
	@Column(name = "depositorname")
	private String depositorName;
	
	@Column(name = "alternatecustomerid")
	private String alternateCustomerId;
	
	@Column(name = "productreference")
	private String productReference;
	
	@Column(name = "appversion")
	private String appVersion;

	public PaymentNotification() {
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSurcharge() {
		return surcharge;
	}

	public void setSurcharge(String surcharge) {
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

	public String getTransactionRef() {
		return transactionRef;
	}

	public void setTransactionRef(String transactionRef) {
		this.transactionRef = transactionRef;
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

	public String getNarration() {
		return Narration;
	}

	public void setNarration(String narration) {
		Narration = narration;
	}

	public String getDepositorName() {
		return depositorName;
	}

	public void setDepositorName(String depositorName) {
		this.depositorName = depositorName;
	}

	public String getAlternateCustomerId() {
		return alternateCustomerId;
	}

	public void setAlternateCustomerId(String alternateCustomerId) {
		this.alternateCustomerId = alternateCustomerId;
	}

	public String getProductReference() {
		return productReference;
	}

	public void setProductReference(String productReference) {
		this.productReference = productReference;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
}
