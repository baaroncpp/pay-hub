package com.payhub.interswitch.service;

import java.util.List;

import com.payhub.interswitch.jpa.entity.PaymentItem;
import org.springframework.data.domain.Pageable;

public interface PaymentItemService {
	
	PaymentItem addPaymentItem(PaymentItem paymentItem);
	PaymentItem updatePaymentItem(PaymentItem paymentItem);
	List<PaymentItem> getPaymnetItems(Pageable pageable);
	PaymentItem getPaymentItem(String paymentCode);

}
