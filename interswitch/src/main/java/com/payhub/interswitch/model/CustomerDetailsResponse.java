package com.payhub.interswitch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CustomerDetailsResponse {	
	
	private String requestReference;
	private String transactionRef;
	private String biller;
	private String customerId;
	private String customerName;
	private String paymentItem;
	private String narration;
	private String amount;
	private Boolean isAmountFixed;
	private String collectionsAccountNumber;
	private String collectionsAccountType;
	private long surcharge;
	private long excise;
	private Boolean SurchargeType;
	private long paymentItemId;
	private long balance;
	private String balanceNarration;
	private String responseCode;
	private Boolean displayBalance;
	private String balanceType;

	public CustomerDetailsResponse() {
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

	public String getBiller() {
		return biller;
	}

	public void setBiller(String biller) {
		this.biller = biller;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPaymentItem() {
		return paymentItem;
	}

	public void setPaymentItem(String paymentItem) {
		this.paymentItem = paymentItem;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Boolean getAmountFixed() {
		return isAmountFixed;
	}

	public void setAmountFixed(Boolean amountFixed) {
		isAmountFixed = amountFixed;
	}

	public String getCollectionsAccountNumber() {
		return collectionsAccountNumber;
	}

	public void setCollectionsAccountNumber(String collectionsAccountNumber) {
		this.collectionsAccountNumber = collectionsAccountNumber;
	}

	public String getCollectionsAccountType() {
		return collectionsAccountType;
	}

	public void setCollectionsAccountType(String collectionsAccountType) {
		this.collectionsAccountType = collectionsAccountType;
	}

	public long getSurcharge() {
		return surcharge;
	}

	public void setSurcharge(long surcharge) {
		this.surcharge = surcharge;
	}

	public long getExcise() {
		return excise;
	}

	public void setExcise(long excise) {
		this.excise = excise;
	}

	public Boolean getSurchargeType() {
		return SurchargeType;
	}

	public void setSurchargeType(Boolean surchargeType) {
		SurchargeType = surchargeType;
	}

	public long getPaymentItemId() {
		return paymentItemId;
	}

	public void setPaymentItemId(long paymentItemId) {
		this.paymentItemId = paymentItemId;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public String getBalanceNarration() {
		return balanceNarration;
	}

	public void setBalanceNarration(String balanceNarration) {
		this.balanceNarration = balanceNarration;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public Boolean getDisplayBalance() {
		return displayBalance;
	}

	public void setDisplayBalance(Boolean displayBalance) {
		this.displayBalance = displayBalance;
	}

	public String getBalanceType() {
		return balanceType;
	}

	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}
}
