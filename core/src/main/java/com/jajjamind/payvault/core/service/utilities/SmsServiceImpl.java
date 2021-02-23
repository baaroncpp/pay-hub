package com.jajjamind.payvault.core.service.utilities;

import com.payhub.notification.api.NotificationApi;
import com.payhub.notification.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author akena
 * 30/01/2021
 * 02:26
 **/
@Service
public class SmsServiceImpl implements SmsService{

    private NotificationApi notificationApi;

    @Autowired
    private SmsServiceImpl(NotificationApi notificationApi){
        this.notificationApi = notificationApi;
    }

    @Override
    public Boolean sendSmsMessage(SmsMessage smsMessage) {

        for(String obj : smsMessage.getDeliverTo()){
            Notification notification = new Notification();
            notification.setNotificationType(smsMessage.getSmsType());
            notification.setAddress(obj);
            notification.setContent(smsMessage.getMessage());
            notification.setSubject("PayHub");

            String status = notificationApi.sendSms(notification).getStatus();
            if(status.equals("Success")){
                return true;
            }
            return false;
        }
        throw new UnsupportedOperationException();
    }
}
