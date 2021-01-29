package com.payhub.interswitch.model;

import java.util.List;

import com.payhub.interswitch.jpa.entity.PaymentItem;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class PaymentItemsModel {
	List<PaymentItem> paymentitems;

	public PaymentItemsModel() {
	}

	public List<PaymentItem> getPaymentitems() {
		return paymentitems;
	}

	public void setPaymentitems(List<PaymentItem> paymentitems) {
		this.paymentitems = paymentitems;
	}
}
