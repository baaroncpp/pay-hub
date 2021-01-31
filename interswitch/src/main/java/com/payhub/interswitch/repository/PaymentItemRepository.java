package com.payhub.interswitch.repository;

import java.util.Optional;

import com.payhub.interswitch.jpa.entity.PaymentItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentItemRepository  extends CrudRepository<PaymentItem, String> ,PagingAndSortingRepository<PaymentItem, String>{

	Optional<PaymentItem> findByPaymentCode(String paymentCode);
}
