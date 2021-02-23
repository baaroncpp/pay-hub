package com.payhub.notification.service.imp;

import com.jajjamind.commons.exceptions.BadRequestException;
import com.jajjamind.commons.text.StringUtil;
import com.jajjamind.commons.time.DateTimeUtil;
import com.jajjamind.commons.utils.EncryptUtil;
import com.jajjamind.commons.utils.RealTimeUtil;
import com.jajjamind.commons.utils.configs.Provider;
import com.jajjamind.commons.utils.configs.ProviderMap;
import com.payhub.notification.constants.Status;
import com.payhub.notification.entities.TSmsMessage;
import com.payhub.notification.model.*;
import com.payhub.notification.network.RetrofitSkylineService;
import com.payhub.notification.repository.SmsMessageRepository;
import com.payhub.notification.service.EmailService;
import com.payhub.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Optional;

@Service
public class NotificationServiceImp implements NotificationService {

    @Autowired
    private RetrofitSkylineService skylineAPIService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SmsMessageRepository smsMessageRepository;

    private static final String FROM_NAME ="PayHub";


    @Override
    public Optional<SmsResponse> sendSms(Notification notification, boolean withException) {

        final String id = RealTimeUtil.generateTransactionId();
        createSMSNotification(notification,id);

        Call<SmsResponse> call = skylineAPIService.getSkylineAPIService().sendSms(skylineAPIService.getSkylineToken(), notification.getAddress(), FROM_NAME, notification.getContent());

        Response<SmsResponse> response = null;

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();

            if(withException) {
                throw new BadRequestException("Failed to send request to SMS Gateway");
            }

            return Optional.empty();
        }

        SmsResponse body = response.body();
        updateSMSNotification(id,body);

        return Optional.of(body);
    }

    private void createSMSNotification(Notification notification,String id){
       final TSmsMessage smsNotification = new TSmsMessage();

        smsNotification.setId(id);
        smsNotification.setContent(StringUtil.isEmpty(notification.getMaskedContent()) ? notification.getContent() : notification.getMaskedContent());
        smsNotification.setCreatedOn(DateTimeUtil.getCurrentUTCTime());
        smsNotification.setStatus(Status.PENDING);
        smsNotification.setStatusDescription("Message has no known state yet");
        smsNotification.setProvider(notification.getProvider().name());
        smsNotification.setRecipient(notification.getAddress());

        smsMessageRepository.save(smsNotification);
    }

    private void updateSMSNotification(String id,SmsResponse response){

        Optional<TSmsMessage> message = smsMessageRepository.findById(id);
        if(message.isPresent()){

            TSmsMessage toUpdate = message.get();
            toUpdate.setReference(response.getReference());

            if(response.getStatus().equalsIgnoreCase("failed")) {
                toUpdate.setStatus(Status.FAILED);
            }else if(response.getStatus().equalsIgnoreCase("success")){
                toUpdate.setStatus(Status.SUCCESS);
            }else{
                toUpdate.setStatus(Status.UNKNOWN);
            }
            toUpdate.setModifiedOn(DateTimeUtil.getCurrentUTCTime());

            smsMessageRepository.save(toUpdate);

        }

    }

    @Override
    public void sendEmail(Notification notification) throws MessagingException {

        if(notification.getAttachmentPath().isEmpty()){
            emailService.sendSimpleEmail(notification.getAddress(), notification.getSubject(), notification.getContent());
        }else{
            emailService.sendMailWithAttachment(notification.getAddress(), notification.getSubject(), notification.getContent(), notification.getAttachmentPath());
        }
    }

    @Override
    public ReportResponse getDeliveryReport(String id) {

        TSmsMessage smsNotification = smsMessageRepository.findById(id).orElseThrow(() -> new BadRequestException("Sms message could not be found"));

        Call<ReportResponse> call = skylineAPIService.getSkylineAPIService().deliveryReports(skylineAPIService.getSkylineToken(), smsNotification.getReference());

        Response<ReportResponse> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ReportResponse reportResponse = response.body();

        if(response.isSuccessful()){

            if(response.body().getDelivery().equals("Delivered")){
               smsNotification.setDelivered(Boolean.TRUE);
               smsNotification.setModifiedOn(DateTimeUtil.getCurrentUTCTime());
               smsMessageRepository.save(smsNotification);
            }
            return reportResponse;
        }else{
            throw new BadRequestException("Failed to access SkyLine");
        }
    }

    @Override
    public BalanceResponse getBalance(){

        Call<BalanceResponse> call = skylineAPIService.getSkylineAPIService().checkBalance(skylineAPIService.getSkylineToken());

        Response<BalanceResponse> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new BadRequestException("Failed to retrieve balance");
        }

        return response.body();

    }
}
