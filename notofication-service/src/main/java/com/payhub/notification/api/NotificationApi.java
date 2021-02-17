package com.payhub.notification.api;

import com.payhub.notification.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationApi {

    @Autowired
    private NotificationApi notificationApi;

    public void sendSms(Notification notification){
        notificationApi.sendSms(notification);
    }

    public void sendEmail(Notification notification){
        notificationApi.sendEmail(notification);
    }
}
