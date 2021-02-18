package com.payhub.notification.repository;

import com.payhub.notification.entities.SmsNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SmsNotificationRepository extends JpaRepository<SmsNotification, String> {
    Optional<SmsNotification> findByReference(String reference);
}
