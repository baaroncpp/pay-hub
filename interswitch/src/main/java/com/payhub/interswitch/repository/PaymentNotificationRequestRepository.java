package com.payhub.interswitch.repository;

import com.payhub.interswitch.jpa.entity.PaymentNotification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PaymentNotificationRequestRepository extends CrudRepository<PaymentNotification, String>, PagingAndSortingRepository<PaymentNotification, String> {

}
