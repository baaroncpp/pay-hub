package com.jajjamind.payvault.core.service.utilities;

import com.jajjamind.commons.exceptions.BadRequestException;
import com.jajjamind.commons.utils.configs.Provider;
import com.payhub.notification.constants.NotificationType;
import com.payhub.notification.entities.TSmsTemplate;
import com.payhub.notification.model.Notification;
import com.payhub.notification.repository.SmsTemplateRepository;
import com.payhub.notification.api.NotificationApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;

/**
 * @author akena
 * 30/01/2021
 * 02:26
 **/
@Service
public class SmsServiceImpl implements SmsService{

    @Autowired
    public SmsTemplateRepository smsTemplateRepository;

    @Autowired
    NotificationApi notificationApi;

    @Override
    public String getSMSFromTemplate(Map<String, String> placeHolders, Sms.Name smsName) {
        final TSmsTemplate template  = smsTemplateRepository.findByNameActive(smsName.name()).orElseThrow(() -> new BadRequestException("SMS template could not be found"));

        String content = template.getContent();

        Iterator<Map.Entry<String,String>>  iterator = placeHolders.entrySet().iterator();

        while(iterator.hasNext()){
            Map.Entry<String,String> obj = iterator.next();
            String key = "##"+obj.getKey()+"##";
            content = content.replaceAll(key,obj.getValue());
        }

        return content;
    }

    @Override
    public void sendSms(String content,String recipient,boolean withException){
        Notification notification = new Notification();
        notification.setAddress(recipient);
        notification.setContent(content);
        notification.setProvider(Provider.SKYSMS);
        notification.setNotificationType(NotificationType.OTP);

        notificationApi.sendSms(notification,withException);
    }

    @Override
    public void sendSms(String content,String maskedContent,String recipient,boolean withException){
        Notification notification = new Notification();
        notification.setAddress(recipient);
        notification.setContent(content);
        notification.setMaskedContent(maskedContent);
        notification.setProvider(Provider.SKYSMS);
        notification.setNotificationType(NotificationType.OTP);

        notificationApi.sendSms(notification,withException);
    }

}
