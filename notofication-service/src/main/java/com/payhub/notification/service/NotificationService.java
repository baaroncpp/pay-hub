package com.payhub.notification.service;

import com.payhub.notification.model.BalanceResponse;
import com.payhub.notification.model.Notification;
import com.payhub.notification.model.ReportResponse;
import com.payhub.notification.model.SmsResponse;

import javax.mail.MessagingException;
import java.io.IOException;

public interface NotificationService {
    SmsResponse sendSms(Notification notification) throws IOException;
    void sendEmail(Notification notification) throws MessagingException;
    ReportResponse getDeliveryReport(String reference) throws IOException;
    BalanceResponse getBalance() throws IOException;
}
