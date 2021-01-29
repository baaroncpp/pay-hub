package com.payhub.data.airtel.entity;

import com.payhub.data.airtel.constant.BundlePaymentStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_bundle_payment", schema = "airtel_data")
public class  BundlePayment {
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bundleid", nullable = false)
	private Bundle bundle;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdon", nullable = false)
	private Date createdOn;
	
	@Column(name = "status", nullable = false)
	private BundlePaymentStatus status;
	
	@Column(name = "customernumber", nullable = false)
	private String customerNumber;

	public BundlePayment() {	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Bundle getBundle() {
		return bundle;
	}

	public void setBundle(Bundle bundle) {
		this.bundle = bundle;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public BundlePaymentStatus getStatus() {
		return status;
	}

	public void setStatus(BundlePaymentStatus status) {
		this.status = status;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
}