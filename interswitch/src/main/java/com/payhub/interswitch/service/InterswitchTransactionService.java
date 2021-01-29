package com.payhub.interswitch.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.payhub.interswitch.jpa.entity.PaymentItem;
import com.payhub.interswitch.jpa.entity.PaymentNotificationResponse;
import com.payhub.interswitch.model.*;
import org.springframework.data.domain.Pageable;


public interface InterswitchTransactionService {
	
	PaymentNotificationResponse makePayment(PaymentModel payment)throws IOException;
	Object getBillers() throws UnsupportedEncodingException, NoSuchAlgorithmException;
	List<PaymentItem> getBillerItems(int billerId) throws UnsupportedEncodingException, NoSuchAlgorithmException;
	TransactionStatusResponse getTransactionStatus(String reference);	
	BalanceInquiryResponse getBalanceIquiry(String inquiryType);	
	Object validateCustomer(CustomerClientModel customer)throws UnsupportedEncodingException, NoSuchAlgorithmException;	

	PaymentNotificationResponse getPaymentNotificationByRequestRef(String requestRef);
	List<PaymentNotificationResponse> getCustomerPaymentNotifications(Pageable pageable, String customer);
	List<PaymentNotificationResponse> getAllPaymentNotifications(Pageable pageable);
	PaymentNotificationResponse sendPaymentNotification(PaymentNotificationClientModel payment);
	Object getCategories()throws UnsupportedEncodingException, NoSuchAlgorithmException;

}
