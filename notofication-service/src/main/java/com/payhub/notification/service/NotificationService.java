package com.payhub.notification.service;

import com.payhub.notification.entities.SmsNotification;
import com.payhub.notification.model.BalanceResponse;
import com.payhub.notification.model.Notification;
import com.payhub.notification.model.ReportResponse;
import com.payhub.notification.model.SmsResponse;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface NotificationService {
    SmsResponse sendSms(Notification notification);
    void sendEmail(Notification notification) throws MessagingException;
    ReportResponse getDeliveryReport(String reference);
    BalanceResponse getBalance();
    SmsNotification getSmsNotification(String id);
    List<SmsNotification> getSmsNotifications(int size, int page);
}
