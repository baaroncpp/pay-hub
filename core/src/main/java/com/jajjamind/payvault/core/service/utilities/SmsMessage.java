package com.jajjamind.payvault.core.service.utilities;

import java.util.List;

/**
 * @author akena
 * 30/01/2021
 * 02:27
 **/
@lombok.Data
public class SmsMessage {
    private String message;
    private List<String> deliverTo;
    private Boolean requireDeliveryReceipt;
    private String smsType;
}
