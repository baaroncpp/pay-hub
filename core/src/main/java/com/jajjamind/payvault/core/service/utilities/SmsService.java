package com.jajjamind.payvault.core.service.utilities;

import java.util.Map;

/**
 * @author akena
 * 30/01/2021
 * 02:25
 **/
public interface SmsService {
    String getSMSFromTemplate(Map<String,String> placeHolders, Sms.Name smsName);
    void sendSms(String content,String recipient,boolean withException);
    void sendSms(String content,String maskedContent,String recipient,boolean withException);
}
