package com.payhub.notification.api;

import com.payhub.notification.model.*;
import com.payhub.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Optional;

@Component
public class NotificationApi {

    @Autowired
    private NotificationService notificationService;

    public Optional<SmsResponse> sendSms(Notification notification, boolean withException) {
        return notificationService.sendSms(notification,withException);
    }

    public void sendEmail(Notification notification) throws MessagingException {
        notificationService.sendEmail(notification);
    }

    public ReportResponse getDeliveryNote(String reference) {
        return notificationService.getDeliveryReport(reference);
    }

    public BalanceResponse getBalance() {
        return notificationService.getBalance();
    }

}
