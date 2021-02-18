package com.jajjamind.payvault.core.service.utilities;

import com.payhub.notification.api.NotificationApi;
import org.springframework.stereotype.Service;

/**
 * @author akena
 * 30/01/2021
 * 02:26
 **/
@Service
public class SmsServiceImpl implements SmsService{


    @Override
    public Boolean sendSmsMessage(SmsMessage smsMessage) {

        NotificationApi notificationApi = new NotificationApi();
        throw new UnsupportedOperationException();
    }
}
