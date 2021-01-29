package com.payhub.data.airtel.repository;

import com.payhub.data.airtel.entity.BundlePayment;
import com.payhub.data.airtel.entity.Bundle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface BundlePaymentRepository extends JpaRepository<BundlePayment, String>{
	
	Page<BundlePayment> findAllByIsPaymentSuccessfullTrue(Pageable pageable);
	
	Page<BundlePayment> findAllByIsPaymentSuccessfullFalse(Pageable pageable);
	
	List<BundlePayment> findAllBycreatedOn(Date date);
	
	List<BundlePayment> findAllBycreatedOnBetween(Date startDate, Date endDate);
	
	Page<BundlePayment> findAllByBundle(Bundle bundle, Pageable pageable);

}
