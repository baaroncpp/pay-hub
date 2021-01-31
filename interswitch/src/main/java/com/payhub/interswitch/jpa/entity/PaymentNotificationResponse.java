package com.payhub.interswitch.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "t_payment_notification_response",schema="interswitch")
public class PaymentNotificationResponse {
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private String id;
	
	@Column(name = "responsemessage")
	private String responseMessage;
	
	@Column(name = "responsecode")
	private String responseCode;
	
	@Column(name = "customer")
	private String customer;
	
	@Column(name = "rechargepin")
	private String rechargePIN;
	
	@Column(name = "transfercode")
	private String transferCode;
	
	@Column(name = "requestreference", unique = true, nullable = false)
	private String requestReference;
	
	@Column(name = "transactionref", unique = true, nullable = false)
	private String transactionRef;
	
	@Column(name = "additionalinfo")
	private String additionalInfo;
	
	@Column(name = "createdon", nullable = false)
	private Date createdon;

	public PaymentNotificationResponse() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
}
