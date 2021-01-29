package com.payhub.interswitch.service.imp;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.payhub.interswitch.jpa.entity.PaymentItem;
import com.payhub.interswitch.repository.PaymentItemRepository;
import com.payhub.interswitch.service.PaymentItemService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class PaymentItemServiceImp implements PaymentItemService {
	
	private PaymentItemRepository paymentItemRepository;
	
	@Autowired
	public PaymentItemServiceImp(PaymentItemRepository paymentItemRepository) {
		this.paymentItemRepository = paymentItemRepository;
	}

	@Override
	public PaymentItem addPaymentItem(PaymentItem paymentItem) {
		// TODO Auto-generated method stub
		Optional<PaymentItem> pi = paymentItemRepository.findByPaymentCode(paymentItem.getPaymentCode());
		
		if(pi.isPresent()) {
			throw new RuntimeException("PaymentItem with Paymentcode: "+paymentItem.getPaymentCode()+" already exists");
		}
		
		paymentItem.setCreatedon(new Date());
		
		return paymentItemRepository.save(paymentItem);
	}

	@Override
	public PaymentItem updatePaymentItem(PaymentItem paymentItem) {
		// TODO Auto-generated method stub
		Optional<PaymentItem> pi = paymentItemRepository.findById(paymentItem.getPaymentCode());
		
		if(pi.isPresent()) {
			return paymentItemRepository.save(paymentItem);
		}else {
			throw new RuntimeException("PaymentItem with Paymentcode: "+paymentItem.getPaymentCode()+" does not exists");
		}
	}

	@Override
	public List<PaymentItem> getPaymnetItems(Pageable pageable) {
		// TODO Auto-generated method stub
		
		Page<PaymentItem> items = paymentItemRepository.findAll(pageable);
		return items.getContent();
	}

	@Override
	@Transactional
	public PaymentItem getPaymentItem(String paymentCode) {
		// TODO Auto-generated method stub
		Optional<PaymentItem> pi = paymentItemRepository.findById(paymentCode);
		
		if(!pi.isPresent()) {
			throw new RuntimeException("PaymentItem with Paymentcode: "+paymentCode+" does not exists");
		}
		return pi.get();
	}

}
