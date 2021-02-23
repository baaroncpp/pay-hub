package com.payhub.notification.repository;


import com.payhub.notification.entities.TSmsMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsMessageRepository extends CrudRepository<TSmsMessage,String> {
}
