package com.payhub.notification.service;

import com.payhub.notification.model.BalanceResponse;
import com.payhub.notification.model.Notification;
import com.payhub.notification.model.ReportResponse;
import com.payhub.notification.model.SmsResponse;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

public interface NotificationService {
    Optional<SmsResponse> sendSms(Notification notification, boolean withException);
    void sendEmail(Notification notification) throws MessagingException;
    ReportResponse getDeliveryReport(String reference);
    BalanceResponse getBalance();
}
