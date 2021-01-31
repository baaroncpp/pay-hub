package com.payhub.interswitch.repository;

import java.util.Optional;

import com.payhub.interswitch.jpa.entity.PaymentNotificationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PaymentNotificationResponseRepository extends CrudRepository<PaymentNotificationResponse, String> ,PagingAndSortingRepository<PaymentNotificationResponse, String>{

	Optional<PaymentNotificationResponse> findByRequestReference(String requestReference);
	Page<PaymentNotificationResponse> findByCustomer(Pageable pageable, String customer);
	
}
