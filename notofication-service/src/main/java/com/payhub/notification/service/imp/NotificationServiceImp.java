package com.payhub.notification.service.imp;

import com.jajjamind.commons.utils.UUIDUtil;
import com.payhub.notification.constants.SmsDelivery;
import com.payhub.notification.entities.SmsNotification;
import com.payhub.notification.model.BalanceResponse;
import com.payhub.notification.model.Notification;
import com.payhub.notification.model.ReportResponse;
import com.payhub.notification.model.SmsResponse;
import com.payhub.notification.network.RetrofitSkylineService;
import com.payhub.notification.network.SkylineAPIService;
import com.payhub.notification.repository.SmsNotificationRepository;
import com.payhub.notification.service.EmailService;
import com.payhub.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImp implements NotificationService {

    @Autowired
    private SkylineAPIService skylineAPIService;

    @Autowired
    private EmailService emailService;

    @Value("${skyline.sms.token}")
    private String token;

    @Value("${message.source.name}")
    private String fromName;

    private SmsNotificationRepository smsNotificationRepository;

    @Autowired
    public NotificationServiceImp(SmsNotificationRepository smsNotificationRepository){
        this.smsNotificationRepository = smsNotificationRepository;
    }

    @Override
    public SmsResponse sendSms(Notification notification) {

        skylineAPIService = RetrofitSkylineService.getSkylineAPIService();
        Call<SmsResponse> call = skylineAPIService.sendSms(token, notification.getAddress(), fromName, notification.getContent());

        String url = call.request().url().toString();

        Response<SmsResponse> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SmsNotification smsNotification = new SmsNotification();

        if(response.isSuccessful()){
            smsNotification.setNotificationType(notification.getNotificationType());
            smsNotification.setId(UUIDUtil.getUUID());
            smsNotification.setMessage(notification.getContent());
            smsNotification.setReference(response.body().getReference());
            smsNotification.setCreatedOn(new Date());

            if(response.body().getStatus().equals("Failed")){
                smsNotification.setSmsDelivery(SmsDelivery.FAILED);
            }else{
                smsNotification.setSmsDelivery(SmsDelivery.UNKNOWN);
            }
            smsNotification.setStatus(response.body().getStatus());

            smsNotificationRepository.save(smsNotification);

            return response.body();
        }else {
         throw new RuntimeException("Failed to access SkyLine");
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
    public ReportResponse getDeliveryReport(String reference) {

        Optional<SmsNotification> smsNotification = smsNotificationRepository.findByReference(reference);
        if(smsNotification.isEmpty()){
            throw new RuntimeException("SmsNotification not found");
        }

        skylineAPIService = RetrofitSkylineService.getSkylineAPIService();
        Call<ReportResponse> call = skylineAPIService.deliveryReports(token, reference);

        Response<ReportResponse> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ReportResponse reportResponse = response.body();

        if(response.isSuccessful()){

            if(response.body().getDelivery().equals("Delivered")){
                smsNotification.get().setStatus("Delivered");
                smsNotification.get().setSmsDelivery(SmsDelivery.SUCCESS);
                smsNotificationRepository.save(smsNotification.get());
            }
            return reportResponse;
        }else{
            throw new RuntimeException("Failed to access SkyLine");
        }
    }

    @Override
    public BalanceResponse getBalance(){

        skylineAPIService = RetrofitSkylineService.getSkylineAPIService();
        Call<BalanceResponse> call = skylineAPIService.checkBalance(token);

        Response<BalanceResponse> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BalanceResponse balanceResponse = response.body();

        return balanceResponse;
    }

    @Override
    public SmsNotification getSmsNotification(String id) {
        Optional<SmsNotification> smsNotification = smsNotificationRepository.findById(id);
        if(smsNotification.isEmpty()){
            throw new RuntimeException("SmsNotification not found");
        }
        return smsNotification.get();
    }

    @Override
    public List<SmsNotification> getSmsNotifications(int size, int page) {
        return null;
    }
}
